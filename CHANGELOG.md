# Engelsystem changelog

## [v.8.0.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.8.0.0)

* Published: 2023-11-22

### Changes

* **Breaking change:** Build with JVM target 11.
* **Breaking change:** Remove `name` in favor of `typeName` property for the sake of its clearer name.
* Add new `typeName` and `typeDescription` properties to `Shift` model. See: https://github.com/engelsystem/engelsystem/pull/1233.
* Replace `Comment` with new clearer named `user_comment` JSON property. Both expose the same value.
* Use kotlin v.1.9.10 and ksp v.1.9.10-1.0.13 & dokka v.1.9.10.
* Use okhttp 4.12.0


## [v.7.3.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.7.3.0)

* Published: 2023-09-12

### Changes

* Use retrofit 2.9.0.
* Use okhttp 4.11.0.
* Use moshi v.1.15.0.
* Use kotlin v.1.8.22 and ksp v.1.8.22-1.0.11 & dokka v.1.8.20.
* Use kotlinx-coroutines-test v.1.7.3.
* Use threetenbp v.1.6.8.


## [v.7.2.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.7.2.0)

* Published: 2022-12-16

### Changes

* Use kotlin v.1.7.22 and ksp v.1.7.22-1.0.8 & dokka v.1.7.20.
* Use moshi v.1.14.0.
* Use kotlinx-coroutines-test v.1.6.4.
* Use threetenbp v.1.6.5.


## [v.7.1.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.7.1.0)

* Published: 2022-05-20

### Changes

* Introduce `EngelsystemApi` interface to ease dependency injection for projects using this library.
  * Remove `@JvmStatic` from `ApiModule#provideEngelsystemService` (only affects Java usage).
* Use kotlin v.1.6.21 and ksp v.1.6.21-1.0.5.


## [v.7.0.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.7.0.0)

* Published: 2022-03-24

### Changes

* **Breaking change:** `EngelsystemService#getShifts` is a `suspend` function now.
* Replace kapt with ksp v.1.6.10-1.0.4.
* Use moshi v.1.13.0.
* Use kotlin v.1.6.10.
* Use kotlinx-coroutines-test v.1.6.0.
* Use threetenbp v.1.6.0.
* Target JDK 11.


## [v.6.0.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.6.0.0)

* Published: 2021-12-10

### Changes

* **Breaking change:** Parse new mandatory `start_date`, `end_date`, `event_timezone` properties into `Shift`.
  * Deprecate `start`, `end`, `timezone` properties in favor of the new ones.
  * Related: https://github.com/engelsystem/engelsystem/issues/695


## [v.5.3.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.5.3.0)

* Published: 2021-11-14

### Changes

* Use threetenbp v.1.5.2.
* Use kotlin v.1.5.31.
* Use kotlinx-coroutines-core v.1.5.2 used in tests.


## [v.5.2.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.5.2.0)

* Published: 2021-07-17

### Changes

* Use kotlin v.1.5.21.
* Use kotlinx-coroutines-core v.1.5.1 used in tests.
* Use threetenbp v.1.5.1.


## [v.5.1.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.5.1.0)

* Published: 2021-04-16

### Changes

* **Important:** Replace Bintray deployment with MavenCentral. New versions will not be available via Bintray/jCenter anymore.
* Use moshi v.1.12.0.
* Use okhttp v.3.12.13.
* Use kotlinx-coroutines-core v.1.4.3 used in tests.
* Use kotlin v.1.4.32.


## [v.5.0.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.5.0.0)

* Published: 2020-11-04

### Changes

* Use threetenbp v.1.5.0.
* Use moshi v.1.11.0. (ProGuard rules are being generated on the fly as of v.1.10.0!)
* Use kotlinx-coroutines-core v.1.3.9 used in tests.
* Use kotlin v.1.4.10.


## [v.4.0.1](https://github.com/johnjohndoe/engelsystem/releases/tag/v.4.0.1)

* Published: 2020-07-15

### Changes

* Use kotlinx-coroutines-core v.1.3.7 used in tests.


## [v.4.0.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.4.0.0)

* Published: 2020-07-15

### Changes

* Remove `UserAgentInterceptor` from public API.
* Replace unneeded `kotlin-coroutines-retrofit` with `kotlinx-coroutines`.
* Use moshi v.1.9.3.
* Use kotlin v.1.3.72.
* Use okhttp v.3.12.12.
* Use threetenbp v.1.4.4.


## [v.3.1.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.3.1.0)

* Published: 2020-01-05

### Changes

* Use okhttp v.3.12.7.
* Use retrofit v.2.6.4.

## [v.3.0.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.3.0.0)

* Published: 2019-12-11

### Changes

* Allow creating custom shifts in tests.


## [v.2.0.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.2.0.0)

* Published: 2019-12-09

### Changes

* Use retrofit v.2.6.3.
* Use time zone offset being provided as part of the server response.
  * Add `timezone` property to `Shift` model.
  * Release the consuming app from having to pass the time zone offset itself.
  * Related: https://github.com/engelsystem/engelsystem/issues/689.
* Replace null-strings with empty strings.


## [v.1.0.0](https://github.com/johnjohndoe/engelsystem/releases/tag/v.1.0.0)

* Published: 2019-12-07

### Changes

* This is the initial release. Have fun!
