# <b>Open Weather API - Android App</b>

## A simple Android application utilising the Open Weather API's to show data regarding the weather in the user requested location.


### Language used
Kotlin.

### Architecture
The application is built on the MVVM Architecture that implements clean architecture.

The MVVM Architecture is a Model-View-ViewModel design that eliminates an encapsulated coupling among the component. The most important thing is that in this model, children do not have direct link to the parent. They just have that reference through the observables.

Model The Model represents data as well as the enterprise logic that runs it’s Android Application. It is comprised of the business logic , remote and local data source model classes as well as the repository.
ViewModel: It consists of the UI Code(Activity fragment), XML. It transmits the user’s action to the ViewModel however it doesn’t not receive the response directly. To receive the response it must subscribe to the observables the ViewModel provides to the ViewModel.
ViewModel This is the bridge that connects View as well as the Model(business reasoning). It doesn’t have any idea of what View must use it , as it doesn’t have any direct connection directly to the View. In essence, the ViewModel is not aware of the View it interacts with. It is in contact in a way with Model and exposes the observer which can be observed by the View.
MVVM  architectural principles

Main two basic principles of MVVM

Separation – Separation of concerns principles says UI-based classes (Activity, Fragment) should only contain logic that handles UI and operating system interactions. By keeping these classes as lean as possible, you can avoid many lifecycle-related problems.
Drive UI from a model – Second principles is you should drive your UI from a model, most preferably a persistent model. Models are components that are responsible for handling the data for an app.

### Technologies used
Jetpack Compose, Retrofit, OkHttp, Koin, Coroutines, JUnit.

### Testing
Junit testing business logic.

### ScreenShot
![image](https://github.com/Daniel-Blandford/OpenWeatherApi_AndroidApp/assets/84602475/f5c75466-cc5b-4cdd-b73e-2a22ee548adf)
