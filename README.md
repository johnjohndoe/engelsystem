[![Build Status](https://travis-ci.org/johnjohndoe/engelsystem.svg?branch=master)](https://travis-ci.org/johnjohndoe/engelsystem) [![JCenter](https://api.bintray.com/packages/tbsprs/maven/engelsystem/images/download.svg)](https://bintray.com/tbsprs/maven/engelsystem/_latestVersion) [![Apache License](http://img.shields.io/badge/license-Apache%20License%202.0-lightgrey.svg)](http://choosealicense.com/licenses/apache-2.0/) [![JitPack](https://jitpack.io/v/johnjohndoe/engelsystem.svg)][jitpack-engelsystem]

# Engelsystem library

![Engelsystem logo](gfx/engelsystem-logo.png "Engelsystem logo")

A Kotlin library containing a parser and models for the Engelsystem:

* https://engelsystem.de


## Usage

```kotlin
ApiModule.provideEngelsystemService(BASE_URL, okHttpClient)
         .getShifts(URL_PART_PATH, API_KEY)
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
        jcenter()
    }
}
```

and to your application module `build.gradle`:


```groovy
dependencies {
    implementation "info.metadude.kotlin.library.engelsystem:engelsystem-base:$version"
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

    Copyright 2019 Tobias Preuss

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
