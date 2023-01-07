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
