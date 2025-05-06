[![Build](https://github.com/johnjohndoe/engelsystem/actions/workflows/build.yaml/badge.svg)](https://github.com/johnjohndoe/engelsystem/actions/workflows/build.yaml) [![Build Status](https://app.travis-ci.com/johnjohndoe/engelsystem.svg?branch=master)](https://app.travis-ci.com/johnjohndoe/engelsystem) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/info.metadude.kotlin.library.engelsystem/engelsystem-base/badge.svg)](https://maven-badges.herokuapp.com/maven-central/info.metadude.kotlin.library.engelsystem/engelsystem-base) [![JitPack](https://jitpack.io/v/johnjohndoe/engelsystem.svg)][jitpack-engelsystem] [![Apache License](http://img.shields.io/badge/license-Apache%20License%202.0-lightgrey.svg)](http://choosealicense.com/licenses/apache-2.0/)

# Engelsystem library

![Engelsystem logo](gfx/engelsystem-logo.png "Engelsystem logo")

A Kotlin library containing a parser and models for the Engelsystem:

* https://engelsystem.de


## Usage

The library is published as two separate artifacts: `engelsystem-base` and `engelsystem-repositories`.
You can use either of them depending on your needs.

#### Usage of `engelsystem-base`

The `engelsystem-base` artifact returns a `Response<List<Shift>>` type from the suspending
`EngelsystemService#getShifts()` function.

```kotlin
val api: EngelsystemApi = Api
val service: EngelsystemService = api.provideEngelsystemService(BASE_URL, okHttpClient)
service.getShifts(
    eTag = "", // Pass an empty string or a previous ETag value for caching
    lastModifiedAt = "", // Pass an empty string or a previous Last-Modified value for caching
    path = URL_PART_PATH,
    apiKey = API_KEY,
)
if (response.isSuccessful) {
    val shifts = response.body()
    val responseETag = response.headers()["ETag"]
    val responseLastModifiedAt = response.headers()["Last-Modified"]
} else {
    val errorCode = response.code()
    val errorMessage = response.message()
}
```

The `engelsystem-repositories` artifact returns a `Flow<GetShiftsState>` type from the suspending
`SimpleEngelsystemRepository#getShiftsState()` function.

```kotlin
val api: EngelsystemApi = Api
val repository = SimpleEngelsystemRepository(
    callFactory = OkHttpClient.Builder().build(),
    api = api,
)

repository.getShiftsState(
    requestETag = "", // Pass an empty string or a previous ETag value for caching
    requestLastModifiedAt = "", // Pass an empty string or a previous Last-Modified value for caching
    baseUrl = BASE_URL,
    path = URL_PART_PATH,
    apiKey = API_KEY,
)
.collectLatest { state: GetShiftsState ->
    when (state) {
        is Success -> {
            val shifts = state.shifts
            val responseETag = state.responseETag
            val responseLastModifiedAt = state.responseLastModifiedAt
        }
        is Error -> {
            val httpStatusCode = state.httpStatusCode
            val errorMessage = state.errorMessage
        }
        is Failure -> {
            val throwable = state.throwable
        }
    }
}
```

### Time stamps

The API response contains time stamps and a corresponding time zone offset.
When the properties of the `Shift` model are read then these two information are joined.


## Gradle build

To deploy the library to your local Maven repository run the following task:

```bash
$ ./gradlew publishToMavenLocal
```

Then, to use the library in your project add the following to
your top level `build.gradle`:

```groovy
allprojects {
    repositories {
        mavenLocal()
    }
}
```

and one of the following dependencies to your application module `build.gradle`:


```groovy
dependencies {
    implementation "info.metadude.kotlin.library.engelsystem:engelsystem-base:$version"
    implementation "info.metadude.kotlin.library.engelsystem:engelsystem-repositories:$version"
}
```

Alternatively, you can use [JitPack][jitpack-engelsystem] to fetch and
build the sources directly from this repository.


## Tests

Run the following command to execute all tests:

```groovy
$ ./gradlew clean test
```

## Author

* [Tobias Preuss][tobias-preuss]

## License

    Copyright 2019-2025 Tobias Preuss

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[jitpack-engelsystem]: https://jitpack.io/#johnjohndoe/engelsystem
[tobias-preuss]: https://github.com/johnjohndoe
