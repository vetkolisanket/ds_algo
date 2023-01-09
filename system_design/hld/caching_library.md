# Caching Library
[Reference](https://github.com/weeeBox/mobile-system-design/blob/master/exercises/caching-library.md)

## Requirements

### Functional requirements
- General purpose caching library
- User should be able to store and retrieve raw bytes of data using string as a key. (We can make even key generic by having a CacheableKey interface and the key that is going to be used can implement this interface)
- User should be able to configure disk and memory limit usage as part of library initialisation
- User should be able to configure eviction policy as part of library initialisation
- User should be able to control if and which of the data stored is encrypted

### Non-functional requirements
- Cached data should be persisted on disk
- A subset of cached data should be stored in memory for quicker access
- Once the cache is full a portion of items should be deleted as per the eviction policy

### Out of scope
- User-defined eviction policies
- Cross-platform support

## Client Public API
Cache:
- init(config: CacheConfig)
- set(key: String, val: [byte]): CacheTask
- get(key: String): CacheTask
- clear(key: String): CacheTask
- clearAll()

CacheConfig:
- init(maxMemomryCacheSize: Int, maxDiskCacheSize: Int)

CacheTask:
- isSuccessful(): Boolean
- getData(): [byte]?
- getErrorMessage(): String?
- addOnCompleteCallback(callback: (CacheTask) -> Unit)

## High Level Diagram
![Caching library high-level diagram](../images/caching-library-hld.png)

### Components
- **Client** - Makes a request to store or retrieve data from the cache.
- **Dispatcher** - Entry point to the caching library, responsible for asynchronously storing or retreiving data.
- **Journal** - Maintains a structural record of metadata of cached items (access count, timestamp, size etc.)
- **In-memory storage** - Limited storage in memory meant for quicker access of items based on some logic to keep it efficient
- **Persistent storage** - Disk storage relatively bigger in size as compared to in memory storage but is relatively slower as well
- **Cache eviction** - Manages cache overflow and item eviction

### Flow
- Client makes a request to store or retrieve data
- Dispatcher uses an available worker to process the request asynchronously. We are doing work asynchronously as item access might lead to blocking I/O operations and doing this work synchronously on the main thread can lead to jank, ANRs and app termination.
- In-memory storage in case of read operations checks if the requested data is available, and returns it if available, else makes call to persistent storage to retrieve data, stores the retrieved data (if available) and returns it back.
- In case of writes the data will be written to persistent storage directly and a copy of it can be stored in in-memory cache for future use.
- Journal keeps a log of every read or write operation and updates the metadata accordingly
- In case of cache overflow, cache eviction decides which data to evict with the help of metadata stored in journal and the pre-defined eviction policy.
- Data is returned to the dispatcher via callbacks (or other asynchronous mechanism which then forwards it asynchronously to the client)
