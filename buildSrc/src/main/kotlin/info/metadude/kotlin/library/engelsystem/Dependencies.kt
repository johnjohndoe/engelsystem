@file:Suppress("SpellCheckingInspection", "unused", "ConstPropertyName")

package info.metadude.kotlin.library.engelsystem

object GradlePlugins {

    private object Versions {
        const val dokka = "2.0.0"
        const val kotlin = "2.1.20"
        const val ksp = "2.1.20-2.0.1"
        const val versions = "0.52.0"
    }

    const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val ksp = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.ksp}"
    const val versions = "com.github.ben-manes:gradle-versions-plugin:${Versions.versions}"
}

object Libs {

    private object Versions {
        const val junitJupiter = "5.12.2"
        const val junitPlatformLauncher = "1.12.2"
        const val kotlinCoroutines = "1.10.2"
        const val mockitoKotlin = "5.4.0"
        const val moshi = "1.15.2"
        const val okhttp = "4.12.0"
        const val retrofit = "2.11.0"
        const val threetenbp = "1.7.1"
        const val truth = "1.4.4"
        const val turbine = "1.2.0"
    }

    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
    const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
    const val junitPlatformLauncher = "org.junit.platform:junit-platform-launcher:${Versions.junitPlatformLauncher}"
    const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val kotlinCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttpMockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val threetenbp = "org.threeten:threetenbp:${Versions.threetenbp}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
}
