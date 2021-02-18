Geofencing - Starter code
========================

Solution code for Advanced Android with Kotlin Codelab

Introduction
------------
A geofence is a virtual perimeter defined by GPS or RFID around a real world area. 
Geofences can be created with a radius around a point location. 

Geofencing has a lot of applications including:

- Reminder apps, such as reminding you to pick up a prescription when you get to a certain destination like your pharmacy.
- Child location services, where a parent can be notified if a child leaves an area designated by a geofence.
- Attendance, where an employer can know when their employees arrive by the time they enter a geofence.
- A treasure hunt app that uses geofences to mark the place where the treasure is hidden. When you enter that place, you will be notified that you have won! - This is what you will be making in this codelab!


Pre-requisites
--------------
- The latest version of Android Studio.
- A minimum of SDK API 29 on your device or emulator. (This should still work on lower API levels but may look differenti.)



Project structure
---------------
`HuntMainActivity.kt` is the main class you will be working in. This class contains skeleton code for functions that handle permissions, adding geofences and removing geofences.
`GeofenceViewModel.kt` is the ViewModel associated with HuntMainActivity.kt. This class handles the LiveData and determines which hint should be shown on screen.
`NotificationUtils.kt`: When you enter a geofence, a notification pops up! This class creates and styles that notification.
`activity_main.xml`: This currently displays an image of an Android but you will implement it to display a hint.
`GeofenceBroadcastReceiver.kt`: This contains skeleton code for the onReceive() method of the BroadcastReceiver where you will handle
