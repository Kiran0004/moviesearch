# moviesearch an Android Native Application

Using this project we can search any movies using OMDB API.
After the search results we can see the complete details  like Actors,Produciton,Director,Language and Awards of the movie.

This is done using Clean Architecture,Modularization and followed MVVM architecture pattern.


This App was created with the objective of explore new features of Android development.

* 100% Kotlin
* Multi Module Gradle Project (feature modules)
* MVVM Android Architecture
* Architecture Components: ViewModel, LiveData, etc.
* Dagger 2.14
* RxJava 2 & RxAndroid 2
* Retrofit 2
* Glide
* Constraint Layout
* GSON
* OMDB API (Series/Filmes)

**Attention: For build project, it's necessary to replace OMDB_API_KEY on `android-common-config.gradle`,`Constants.kt` for a [valid key](http://www.omdbapi.com/apikey.aspx).**

#### Project Structure
This project is built using Clean Architecture and it has modules in the following way:

home,app - contains Activities/Fragments and their Adapters for the presentation layer

core - contains API details and netowrk handling

details - contains implementation details for network(Retrofit/Room/SQLDelight/Realm)

#### Project Checkout and application running process

Steps to run the project using the command line:

Get the project locally:

git clone https://github.com/Kiran0004/moviesearch.git

Navigate to the /app folder and execute assemblDebug command from Gradle Wrapper:

./gradlew assembleDebug After the build, **app-debug.apk** can be found inside your project dir using this path app/build/outputs/apk/debug/

Using adb install project directly to a device or emulator using the command below:

adb install app/build/outputs/apk/debug/app-debug.apk

You can also use Android Studio for that purpose either: VSC -> Git -> Clone Insert URL https://github.com/Kiran0004/moviesearch.git