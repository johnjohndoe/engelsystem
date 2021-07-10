# Engelsystem changelog

## NEXT

* Not published yet.

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
