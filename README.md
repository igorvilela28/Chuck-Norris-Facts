# Chuck Norris Facts
[![Build Status](https://app.bitrise.io/app/9d7d60cad3099158/status.svg?token=6vSPd9qQzmW8lBs3v7wJ2A)](https://app.bitrise.io/app/9d7d60cad3099158)

The goal of this project is to demonstrate skills on Android development as well as to apply some of the best practices in Kotlin development.

The project aims to replicate real world development scenarios, with a well defined architecture, unit and integration tests. It also has the setup for generate a integrated test coverage report and to obfuscate the code on production builds, using proguard.

## Stack

The main technologies and concepts used are:

- Kotlin
- Clean Architecture + MVVM on presentation
- Multi gradle modules
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) along with the experimental [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) (ViewModel + LiveData + Room)
- Unit tests using [Junit](https://junit.org/junit4/) + [MockK](https://mockk.io/) + [Robolectric](http://robolectric.org/)
- Integration tests using [Espresso]() and following the [Robot Pattern](https://jakewharton.com/testing-robots/)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)

For a list of all project dependencies, you may run this gradle command: `./gradlew <module>:dependencies` where *<module>* is one of the gradle modules on this project.

## Setup

This application was build using the Android Gradle Plugin 3.3.2, Gradle 4.10.1 and Kotlin 1.3.21. In order to import it on Android Studio, make sure your setup meet these requirements. It also makes heavy use of the AndroidX libraries and it's important that the [jetifier](https://developer.android.com/studio/command-line/jetifier) is enabled on the [gradle.properties](https://github.com/igorvilela28/Chuck-Norris-Facts/blob/master/gradle.properties) file.

## Build and tests

The follow gradle command will assembles and unit test this project

```
./gradlew build
```

After building the project, you will be able to grab the generated artifact apks from `{projectPath}/app/build/outputs/apk` and install on some device.

To install it using the command line, use

```
./gradlew install<BuildType>
```

where *<BuildType>* is one of the following: *Debug*, *Release*

The following command will run all tests (unit + integration) and generate a coverage report that can be found at `{projectPath}/build/reports/jacoco/jacocoMergeReport/html/index.html`

```
./gradlew jacocoMergeReport
```

***IMPORTANT:*** For run the integration tests, you'll need to have an online AVD or Android Device. I recommend that your device or AVD follows at least this [setup](https://github.com/igorvilela28/Chuck-Norris-Facts/blob/master/.github/avd_setup.txt).

You also could run all commands together, ensuring the build and tests are okay before installing it, using:

```
./gradlew build jacocoMergeReport install<BuildType>
```

## LICENSE

```
The MIT License (MIT)

Copyright (c) 2019 Igor Vilela

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
