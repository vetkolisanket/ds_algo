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
Most standard third-party libraries latest versions support Android 21 and above
TODO: List down points for this question

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
* **Persistence** - Single source of truth. The data your system receives from the API service gets persisted on the disk first and then propogated to other components
* **Repository** - A mediator between API service and persistence.
* **Tweet feed flow** - Component responsible for displaying infinite list of tweets
* **Tweet detail flow** - Component responsible for displaying a single tweets details
