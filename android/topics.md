# Topics

## Interview questions
- What is context in Android?
  - Context is the fundamental concept that represents the currents state and environment in which application is running. It provides
access to resources, system services and information related to application and its execution environment. Almost every android component
requires context to perform tasks like:
      - get access to resources like layouts, strings and drawables
      - start activities, services and broadcast intents
      - get access to databases using SQLiteOpenHelper
      - get information related to application like its package name and version code
      - access system resources like LocationManager, NotificationManager and more
   
- What is the Android operating system? How is it different from other mobile operating systems?
  - Android operating system is an open-sourced, Linux-based operating system primarily designed for mobile devices like smartphones,
    tablets, smartwatches and other gadgets. It is different from other operating systems in that it is open-source, versatile,
    customizable, has a diverse app ecosystem and wide range of device support with strong integration with Google services like
    Google Search, Google Maps, Gmail, Google Play Services, FCM Notifications etc
- Describe the Android application architecture.
  - The android application architecture is based on the principles of MVP, MVVM or MVI design patterns depending on the chosen
    architecture components. It provides a structured and modular approach of android application development, promoting separation of
    concerns and maintainability.
- What is an activity in Android?
  - An activity is the fundamental building block of an android application that represents a single screen with user interaction. It is responsible for managing user interactions and presenting the visual elements to the user.
- What is an intent in Android?
  - Intent is a messaging object that facilitates communication between different components of an application or within different applications. Intents are of two types, **implicit** intents and **explicit** intents
      - **Implicit Intent** - Implicit intents do not specify an explicit target component and let the system find the appropriate component on the basis of action and data provided in the intent
      - **Explicit Intent** - In an explicit intent we specify the target component that needs to be started to handle the intent and its data by providing the components class name
- What are out-of-memory errors in Android. Common happenings, how to prevent, benchmarking
  - Out-of-memory errors (OOM errors) in Android occur when an application tries to allocate more memory than the system can provide. This situation typically arises when the application consumes excessive memory, leading to the Android system running out of available memory resources. When an OOM error occurs, the Android system terminates the application to free up memory, resulting in a crash.
  - Common Causes of Out-of-Memory Errors:
      - **Large Bitmaps**: Loading and handling large bitmaps, especially without proper memory management, can quickly consume a significant amount of memory, leading to OOM errors.
      - **Memory Leaks**: If an application retains references to objects that are no longer needed, such as activities, fragments, or other data structures, it can lead to memory leaks, gradually depleting the available memory.
      - **Excessive Data Loading**: Loading and caching large amounts of data from web services or databases without proper caching and handling can strain memory resources.
      - **Inefficient Code**: Poorly optimized code that inefficiently uses memory or creates unnecessary objects can lead to excessive memory consumption.
      - **Using Non-Optimized Libraries**: Using third-party libraries that are not memory-optimized or contain memory leaks can contribute to OOM errors.
  - Ways to Prevent Out-of-Memory Errors:
      - **Efficient Bitmap Handling**: Use techniques like image resizing, downsampling, and image caching to manage bitmap memory efficiently.
      - **Memory Management**: Release references to objects when they are no longer needed, especially in long-lived components like activities and fragments.
      - **Memory Caching**: Use memory caching mechanisms for frequently accessed data to avoid redundant memory allocation.
      - **Optimize Data Loading**: Load and process data in a chunked or streaming manner to avoid loading large datasets all at once.
      - **Profiling and Memory Analysis**: Use Android Profiler and other memory analysis tools to identify memory bottlenecks and optimize memory usage.
      - **Use Libraries Wisely**: Choose well-optimized and memory-efficient libraries to avoid potential memory-related issues.
  - Benchmarking and Testing:
      - Benchmarking and testing your application's memory usage is essential to ensure its stability and performance. Here are some approaches to benchmarking and testing for memory-related issues:
      - **Emulator and Device Testing**: Test your application on different Android devices with varying memory capabilities to ensure it performs well across different platforms.
      - **Stress Testing**: Perform stress tests that simulate heavy usage scenarios to identify potential memory leaks or OOM errors.
      - **Memory Profiling**: Use Android Profiler or third-party profiling tools to monitor and analyze memory usage during different scenarios of your application.
      - **Leak Detection Tools**: Employ leak detection libraries like LeakCanary to automatically detect and report memory leaks during application execution.
      - **Heap Dump Analysis**: Take heap dumps during testing and analyze them to identify objects that are not being properly garbage collected.
      - **Testing on Low-end Devices**: Ensure that your application functions correctly on low-end devices with limited memory resources.
  - By following good memory management practices, regularly testing your application for memory-related issues, and using appropriate tools for profiling and leak detection, you can prevent out-of-memory errors and deliver a stable and efficient Android application.
-  Compose related interview questions
  - Jetpack Compose vs Android View System.
  - Explain the concept of declarative UI in Jetpack Compose.
  - Declarative UI vs Imperative UI.
• What are Composable functions?
• What is Recomposition?
• What is State in Compose?
• How does state management work in Jetpack Compose?
• Stateful composable vs Stateless composable.
• What are the side effects?
• Difference between LaunchedEffect and DisposableEffect.
• What is rememberCoroutineScope and its use cases?
• How to observe Flows, and LiveData states in Compose UI?
• How can we handle asynchronous operations in Jetpack Compose?
• How can we convert a non-compose state into a Compose state?
• Explain derivedStateOf.
• Explain rememberUpdatedState.
• Difference between remember and rememberSaveable.
• Explain the Lifecycle of a Composable in Jetpack Compose.
• How do you handle lifecycle events in Compose functions?
• What are the best practices for performance optimization in Jetpack Compose?
• Can we use both Jetpack Compose and Android View in a Single App?
• What is State Hoisting?
• Explain CompositionLocal.
• Explain Jetpack Compose Phases.
• What is the role of the Modifier in Jetpack Compose?
• What are Semantics?
• How can you handle user input and events in Jetpack Compose?
• How do you handle navigation in Jetpack Compose?
• How do you handle orientation changes in Jetpack Compose?
• Explain the concept of unidirectional data flow in Jetpack Compose.
• How to create Custom Layouts in Compose?
