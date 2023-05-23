# The Film List
A simple Android app about **a movie app with data gotten from The Movie DB**. It was made with Jetpack Compose, and it uses Koin as Dependency Injection for providing all class dependencies in each module in the app. Also, the app uses asynchronous flow to link the ViewModel with UI request operations to the movie API. Also, this project is organized with a clean architecture that can help you organize your project.

# Screenshots

| ![Movie App - 1](https://i.postimg.cc/9Q6Xzc59/Screenshot-1684863088.png) | ![Movie App - 2](https://i.postimg.cc/Wb41H9wd/Screenshot-1684863130.png) | ![Movie App - 4](https://i.postimg.cc/c45LyQFX/Screenshot-1684863126.png) | ![Movie App - 5](https://i.postimg.cc/7ZGYxfcD/Screenshot-1684863168.png) |
|:-------------------------------------------------------------------------:|:-------------------------------------------------------------------------:|:-------------------------------------------------------------------------:|:-------------------------------------------------------------------------:|

<div style="text-align: center;">
  <img src="https://i.postimg.cc/QCpKYByZ/movie-app.gif" width="200px">
</div>

---

The following is a list of the tools and libraries that it were used in this app.

## Android Components:
* [Jetpack Compose][1]
* [ViewModel][2]
* [Compose Navigation][3]

## Dependency Injection:
* [Koin][4]

## Asynchronous Programming:
* [Kotlin Coroutines][5]
* [Asynchronous Flow][6]

## Accompanist:
* [Pager][7]
* [System IU Controller][8]

## HTTP Client:
* [Retrofit][9]

## Animation:
* [Lottie Files][10]

---

If you clone this project you may have to create a User in The Movie DB and you should will request a **API KEY** and save it in your local file **gradle.properties** and you must also create a **buildConfigField** property in your **build.gradle** in your **app** module and access it programmatically by calling **BuildConfig.name_that_you_put**, for more information read [Gradle tips and recipes][11]

[1]: https://developer.android.com/jetpack/compose
[2]: https://developer.android.com/topic/libraries/architecture/viewmodel
[3]: https://developer.android.com/jetpack/compose/navigation
[4]: https://insert-koin.io/
[5]: https://kotlinlang.org/docs/coroutines-overview.html
[6]: https://kotlinlang.org/docs/flow.html
[7]: https://google.github.io/accompanist/pager/
[8]: https://google.github.io/accompanist/systemuicontroller/
[9]: https://square.github.io/retrofit/
[10]: https://airbnb.io/lottie/#/android-compose
[11]: https://developer.android.com/build/gradle-tips