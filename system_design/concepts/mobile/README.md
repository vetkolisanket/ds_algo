# Mobile System Design Concepts

## Things to study/revise

- [ ] Login/Authentication
    - [ ] [401 Unauthorised](authentication/401-unauthorised.md)
    - [ ] 429 Too many requests
- [ ] Emerging market challenges
    - [ ] App size optimisations
    - [ ] Low-end device optimisations
    - [ ] Bandwidth optimisations/network/caching/exponential backoff
    - [ ] CPU/Battery usage optimisations
- [ ] Number of users related queries
    - [ ] Exponential backoff
    - [ ] API rate-limiting
- [ ] Team size
    - [ ] Smaller team (2-4 people) - People can work together easily, app can be a single module if needed
    - [ ] Larger team (20-100 people) - App needs to be broken down into multiple modules which will be handled by different teams
- [ ] HLD
- [ ] CDN
- [ ] DI Graph component/ Dependency Injection
- [ ] Coordinator/Navigation component
- [ ] Multi-module apps
- [ ] MVP vs MVC vs MVVM vs MVI
- [ ] Image loading
- [ ] Scrolling optimizations
- [ ] Use cases
- [ ] API Design
- [ ] How an HTTP connection is made
- [ ] Real-time notifications
    - [ ] Push Notifications pros and cons
    - [ ] HTTP polling pros and cons
        - [ ] Short HTTP-polling pros and cons
        - [ ] Long HTTP-polling pros and cons
    - [ ] Server-Sent Events pros and cons
    - [ ] Web sockets
    - [ ] Difference between Long HTTP-polling and SSE
- [ ] Protocols
    - [ ] REST pros and cons
    - [ ] GraphQL pros and cons
    - [ ] WebSocket pros and cons
    - [ ] gRPC pros and cons
- [ ] Pagination
    - [ ] Offset pagination
    - [ ] Keyset pagination
    - [ ] Cursor/Seek pagination
- [ ] Data Storage
    - [ ] Key-Value Storage (SharedPreferences)
    - [ ] Data Store (Custom/Binary)
    - [ ] Database/ORM
    - [ ] On-Device Secure Storage (KeyStore/Key Chain)
- [ ] Storage Location
    - [ ] Internal
    - [ ] External
    - [ ] Media/Scoped
- [ ] Storage Type
    - [ ] Document
    - [ ] Cache
    - [ ] Temp
- [ ] Storage Best practices
    - [ ] Store as little data as possible
    - [ ] Use encrypted storage if you can’t avoid storing sensitive data
    - [ ] Do not allow app storage to grow uncontrollably. Do periodic cleanups
- [ ] Security
    - [ ] [SSL pinning why and how](ssl-pinning.md)
    - [ ] On-device secure storage (KeyStore/Key Chain)
    - [ ] Proguard & Dexguard
- [ ] Performance
    - [ ] Firebase performance
    - [ ] Memory leaks
    - [ ] Adding custom traces
    - [ ] Profiler
    - [ ] Best practices
    - [ ] Slow rendering & Frozen frames
- [ ] Testing
    - [ ] Unit testing
    - [ ] Integtation testing
    - [ ] Roboelectric

## Communication between client and server

### Stateless communication b/w client and server (REST)
Stateless communication - Every communication between the client and the server is like a new one. No information (state) is held from a previous 
communication or to a follow-up communication in future

### Bidirectional communication using web sockets
Both client and server can get data from each other. Needs persistent connection. More complicated to implement than stateless communication

### Unidirectional communication using Server-Sent Events (SSE)
Allows the client to stream events over an HTTP/1.1 connection without polling. Communication is carried out from server to client, client cannot send data
to server
   #### Pros:
   * real-time traffic using a single connection.
   #### Cons:
   * keeps a persistent connection.

#### Why use SSE over Web sockets?
SSEs are sent over traditional HTTP. That means they do not require a special protocol or server implementation to get working. WebSockets on the other hand, require full-duplex connections and new Web Socket servers to handle the protocol. In addition, Server-Sent Events have a variety of features that WebSockets lack by design such as automatic reconnection, event IDs, and the ability to send arbitrary events.

#### Resources to read more about SSE
* [Article on how to implement SSE server client communication in android](https://proandroiddev.com/unidirectional-server-client-communication-using-sse-in-android-79b825aa0670)

## Pagination

Why pagination? 
To prevent excessive network and memory usage and to prevent timeouts. A user at a given time consumes only a few sets/lists of content. Fetching say 2X of what he is going to consume on an average should suffice. If user comes near to the end of the data fetched, we can prefetch the next page of data. Prefetching helps improve user experience.

### Different ways of pagination

1. **Offset pagination** - Fetch data at a specific offset e.g. GET /feed?offset=100&&limit=10
   - **Pros**
     - Simple to implement
     - Stateless on the server
   - **Cons**
     - Bad performance on large offset values (The database has to skip offset rows before returning the paginated result)
     - Inconsistent when adding new rows to the database (Page drifting)
     
2. **Keyset pagination** - Uses the value from the last page to fetch the next set of items e.g. GET /feed?after=2021-05-25T00:00:00&limit=20.
   - **Pros**
     - Translates easily into SQL query
     - Good performance onto large data sets
     - Stateless on the server
   - **Cons**
     - "leaky abstraction" - the pagination mechanism becomes aware of the underlying database storage.
     - only works on fields with natural ordering e.g. timestamp etc.
      
3. **Cursor/Seek pagination** - Operates on stable ids which are decoupled from database SQL queries (usually, a selected field is encoded using base64 and encrypted on the backend side) e.g. GET /feed?after_id=t1234xzy&limit=20.
   - **Pros**
     - decouples pagination from SQL database
     - consistent ordering when new items are inserted
   - **Cons**
     - more complex backend implementation
     - does not work well if items get deleted (ids might become invalid)
     
Discuss all the options possible with their pros and cons and select the one which seems more apt

