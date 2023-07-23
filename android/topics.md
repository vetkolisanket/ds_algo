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
