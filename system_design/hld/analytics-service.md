# Analytics Service
- Design a general purpose analytics manager
- The manager would send events to remote analytics service which would process them and make it available to product/business team to take next decisions.

## Requirement Gathering
- System should be able to send events to the servers of analytics server providers
- Our system should be accessible from anywhere (across the app)
- Should be easy to use `fun track(event: Event)` or `fun log(event: Event)`
- Should be able to send multiple events
- Each event can have multiple parameters
- Should be able to set/unset user properties
- Should be able to support multiple Analytics Service Providers (multiple sdks will be used e.g. Clevertap, Firebase, Google Analytics, Custom backend, MoEngage, Amplitude)
- Should be able to send different events to different analytics services
- For the same event, event name and/or properties can be different for different analytics services
- System should be scalable i.e. events and parameters can be added/removed easily
- System should be testable
- Should be able to work in low/no network conditions (should store events in offline state and send them when the system is online)

## Identifying technical requirements
- **Accessible from anywhere** - (Analytics manager) Inject instance for each entity or use Singleton. I'd prefer singleton here as it is simpler and its sole job is to get data from entities and pass to the network layer or analytics services
- **Multiple analytics services** - Our analytics manager should have an instance of all of them. Single method should trigger the track or send method of all of them
- **Event name or params can change from service to service** - Each analytics service should be treated as a separate entity. To logic to change event name or param should be within this entity
- **Should be testable** - Always work with interfaces/contracts so that instances are easily replaceable with mocks for testability

## High level diagram
![High level diagram](../images/analytics-service-hld.svg)

### Components

```
Event
+ name: String,
+ properties: Map<String, Any>
```

```
IAnalytics
+ init(application: Application)
+ send(event: Event)
+ setUser(userProperties: Map<String, Any>)
+ unSetUser()
```

```
IAnalyticsService: IAnalytics
+ shouldTrack(): Boolean
```

```
AnalyticsManager: IAnalyticsService
- constructor()
+ instance: AnalyticsManager
+ services: List<IAnalyticsService>
```

#### Deep dive components
- **Event** - An event is the basic building block which will be sent to the analytics servers. Each event will contains a name and list of properties. Each property having a name and a value associated with it. The analytics manager will receive events and pass them down to individual analytics services which will then do the necessary transformations required on their end and process them
- **IAnalytics** - A base inteface which will be extended by `IAnalyticsService` and `AnalyticsManager`. It will contain the basic methods needed for the functioning of an analytics service like initialise, send event, set/unser user properties.
- **IAnalyticsService** - This is another interface which will be extending the `IAnalytics` interface. It will contain a method `shouldTrack(): Boolean` which will tell whether an event be sent by a particular Analytics service.
- **AnalyticsManager** - This will be a singleton which will be implementing the `IAnalytics` interface and maintain a list of analytics services to which the events need to be forwarded
- **Misc** - Each analytics service would contain a should track function which will contain the logic of whether or not a particular event be sent to that analytics servcie, each analytics service can contain a getParams function which will take the params from the send(params: Map<String, Any>) function and filter out the ones that need not be sent to that service



[Reference](https://www.youtube.com/watch?v=pfrjGFDXRt4&ab_channel=iCode)
