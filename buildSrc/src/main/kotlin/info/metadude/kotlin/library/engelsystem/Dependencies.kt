@file:Suppress("SpellCheckingInspection", "unused")

package info.metadude.kotlin.library.engelsystem

object GradlePlugins {

    private object Versions {
        const val dokka = "1.5.31"
        const val kotlin = "1.5.31"
        const val versions = "0.42.0"
    }

    const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val versions = "com.github.ben-manes:gradle-versions-plugin:${Versions.versions}"
}

object Libs {

    private object Versions {
        const val junitJupiter = "5.8.2"
        const val kotlinCoroutines = "1.5.2"
        const val moshi = "1.12.0"
        const val okhttp = "3.12.13"
        const val retrofit = "2.6.4"
        const val threetenbp = "1.6.0"
        const val truth = "1.1.3"
    }

    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
    const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
    const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val threetenbp = "org.threeten:threetenbp:${Versions.threetenbp}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
}
