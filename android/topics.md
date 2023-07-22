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
