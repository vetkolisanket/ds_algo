# Design Twitter Feed

## Requirement Gathering

### Functional requirements
* Users should be able to scroll through infinite list of tweets
* Users should be able to like a tweet
* Users should be able to open a tweet and see comments

### Non-functional requirements
* Offline support
* Real-time notifications
* Optimal bandwidth and CPU/Battery usage.

### Out of scope
* Login/Authentication
* Tweet sending
* Followers/Retweet
* Analytics

## Clarifying questions
1. Do we need to support emerging markets?

- **Yes** - The app should be as small as possible. [For every 6 Mb increase in app size, we lose 1% users during installation](https://medium.com/googleplaydev/shrinking-apks-growing-installs-5d3fcba23ce2). The app should be optimized for low-end devices and slower network. The app should limit the number and frequency of network requests and rely heavily on caching. There should be higher focus on making the app offline first. We would also have to think of supporting lower OS versions of android
- **No** - More focus on functional requirements/features than on non-functional requirements.

2. What is the lowest OS version we want to support?
- Most standard third-party libraries latest versions support Android 21 and above
- TODO: List down points for this question

3. What number of users do we expect?

- **A few hundred users** - Lesser focus on limiting the number and frequency of network requests.
- **Million users** - Should use caching wherever possible/applicable. Discuss exponential back-off and API rate limiting.

4. Is the user group controlled or anyone can install and use the app?

- **User group controlled** - Lesser focus on making the app backward compatible, can discuss how to implement force upgrade.
- **Anyone can install the app** - Think of backward compatibility and keeping features configurable via backend or remote config. A/B experimenting new features, staged rollout etc.

5. How big is the engineering team?

- **Small (2-4) people** - Lesser focus on breaking the app into modules
- **Big (20-100) people** - Should describe breaking down features/layers into modules so that different teams can work independently on different modules. In case of twitter we can discuss breaking down app into app, feeds, network/communication, config, logger modules.

## High-level diagram


### Server-side components
* **Backend** - Abstraction for the server/backend where we make api call/network requests to get data to be rendered. In case of this problem we can have following api calls
- GET v1/feeds?limit=x&&offset=y
- GET v1/feed/<feed-id>
  
* **Push provider** - Represents push provider infrastucture. Receives push payload from backend and delivers it to clients. This can be FCM or Mi push or some other provider. This can be used to notify user of important events or updates/updates. In case of this problem, we might want to notify user when some connection of a user publishes a tweet or someone likes/comments/retweets users tweet.
  
* **CDN (Content Delivery Network)** - Responsible for delivering static content to clients. For this example it can be images

### Client-side components
* **API Service** - Abstracts client-server communication from the rest of the system.
  - Responsible for calling getFeeds() and getFeed() (details) function. Most probably will contain retrofit and okhttp components
* **Persistence** - Single source of truth. The data your system receives from the API service gets persisted on the disk first and then propogated to other components
  - Will get data from repository to save in db and pass back to be displayed on UI. Will contain saveFeeds(), saveFeedDetails(), getFeeds() and getFeedDetails() functions
* **Repository** - A mediator between API service and persistence.
  - Will get requests from viewmodels to get data. Depending on the network state will get data from API service, pass it to be saved in persistence or get saved data from persistence
* **Tweet feed flow** - Component responsible for displaying infinite list of tweets
  - Will consist of TweetFeedsActivity/Fragment and TweetFeedsViewModel. The activity/fragment will render a list of tweets and request the viewmodel to get paginated feeds and to go to TweetDetails flow on click of a particular feed. The TweetFeedsViewModel will get requests from activity/fragment and get data to fulfill the requests from repository and notify the activity/fragment on fetching the data
* **Tweet detail flow** - Component responsible for displaying a single tweets details
  - Will consist of TweetFeedDetailsActivity (UI) and TweetFeedDetailsViewModel. The UI will request the viewmodel to get details of a tweet and render it on receving the data. The ViewModel will get the data from repository and notify the UI via livedata
  
### Deep Dive
#### Tweet Feed Flow
  - **Architecture Pattern** - MVVM would be preferrable because of its benefits like loose coupling between View and ViewModel and ViewModel surviving configuration change and being lifecycle aware. MVP can also be used for its simplicity. 
  - **Flow** - UI (Activity/Fragment) will make request to view model to get data. The view model can contain the pagination logic and trigger get paginated feeds use case which will make paginated request to repository to get data. The repository will call API service to get data. On receiving data from API service, the repository can persist the data in persistence and notify the UI about the data. The UI can update itself accordingly. For pagination we can make the initial call as soon as the UI loads (onCreate after initialising view model and observers). For pagination we can make the call in a prefetching manner (eager fetch) i.e. as soon as the user comes to (n-5)th item we fetch the next paginated data, where n is the no. of items in the feed. That improves user experience.
  - **Pagination** - For pagination we can go with offset cursor/seek pagination as offset pagination can lead to issues (duplicates or miss some data) and keyset pagination might not be possible as our feed logic can be complicated and may not have some sort of ordering, but some sort of logic over offset or keyset pagination should also be fine
  - **Dependency Injection** - Helps to build an isolated and testable module. In this case the view model will be provided by view model provider. The repository, persistence and API service can be provided using dagger or hilt.
  - **Image Loading** - As of now my standard answer would be use glide or picasso, but I should know their internal workings. I should discuss about low res vs full res image loading, assigning priority to images being loaded, loading a low res + high res image and replacing the low res image with high res on its load to improve user experience. I can discuss 
    - caching
      - disk cache
      - memory cache
    - caching strategies
      - LRU
      - LFU
    - mapping url to key
  
##### Components
  - **Feed API Service** - abstracts Twitter Feed API client. Provides the functionality to request paginated data from the backend. Injected via DI-graph
  - **Feed persistence** - abstact cached paginated data storage. Injected via DI-graph.
  - **Get paginated feeds use case** - triggers fetching next/prev page of data. Redirects the newly fetched page response into a persistence layer.
  - **Feed repository** - consolidates remote and cached responses into a Pager object through Remote Mediator
  - **Tweet like use case** - triggers tweet like/unlike flow (calls API)
  - **Tweet details use case** - triggers tweet details flow (opens Tweet Details UI)
  - **Image loader** - abstracts image loading from image loading library. Injected via DI-graph.
  
#### Tweet Detail Flow
  - **Architecture Pattern** - Would go ahead with MVVM. But can work with MVP also if needed.
  - **Flow** - UI will receive the id of the tweet whose detail needs to be loaded via intent from tweet feeds UI or via deeplink or notification. UI will request the view model to fetch the details for this tweet id. View model will trigger the Get tweet details use case which will use feed repository to get tweet details. The repository will hit the Feed API service to get data, store the successful response in the persistence layer and notify UI to update view
  - **Dependency Injection** - The view model will be provided by view model provider. The repository, persistence and API service can be provided using dagger or hilt.
  - **Image Loading** - Same as for tweet feed flow.
  
### API Design
  - GET v1/feeds?limit=x&&offset=y
  - GET v1/feed/<feed-id>
  - PUT v1/feed/<feed-id>?action=<like or unlike>
  
### Realtime notifications
  - Push notifications - to notify someone liking your tweet
  - Server-sent events - to update like count on a real-time basis
  
### Protocols
  - Can go ahead with REST for its simplicity and it suits the requirements
  
### Pagination
  - Can go ahead with offset pagination for its simplicity. We can include the first tweets id to work around some of the issues of offset pagination. If the team is comfortable around working with cursor/seek pagination we can go ahead with that as well
  
### Authentication
  
### Data storage
  
#### Storage Location
  
#### Storage Type
  
#### Best Practices
  
