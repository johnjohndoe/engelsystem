package info.metadude.kotlin.library.engelsystem

private const val kotlinVersion = "1.3.61"

object GradlePlugins {

    private object Versions {
        const val bintray = "1.8.4"
        const val versions = "0.27.0"
    }

    const val bintray = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Versions.bintray}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val versions = "com.github.ben-manes:gradle-versions-plugin:${Versions.versions}"
}

object Libs {

    private object Versions {
        const val junitJupiter = "5.6.0"
        const val kotlinCoroutinesRetrofit = "1.1.0"
        const val moshi = "1.9.2"
        const val okhttp = "3.12.7"
        const val retrofit = "2.6.4"
        const val threetenbp = "1.4.0"
        const val truth = "1.0.1"
    }

    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
    const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
    const val kotlinCoroutinesRetrofit = "ru.gildor.coroutines:kotlin-coroutines-retrofit:${Versions.kotlinCoroutinesRetrofit}"
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val threetenbp = "org.threeten:threetenbp:${Versions.threetenbp}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
}
