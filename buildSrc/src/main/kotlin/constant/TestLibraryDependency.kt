package constant

import constant.LibraryDependency.Coroutines

object TestLibraryDependency {
    object Version {
        const val junit: String = "4.13.2"
        const val archTest: String = "2.1.0"
        const val truth: String = "1.1.2"
        const val mockk: String = "1.11.0"
        const val mockWebServer: String = "4.9.1"

        const val robolectric: String = "4.3.1"

        const val androidxTest: String = "1.3.0"
        const val testExt: String = "1.1.2"
        const val espresso: String = "3.3.0"
        const val fragment: String = "1.3.3"
    }

    const val roomTest: String =
        "androidx.room:room-testing:${LibraryDependency.Database.Version.room}"

    private const val junit: String = "junit:junit:${Version.junit}"
    private const val archTest: String = "androidx.arch.core:core-testing:${Version.archTest}"
    private const val truth: String = "com.google.truth:truth:${Version.truth}"
    private const val mockk: String = "io.mockk:mockk:${Version.mockk}"
    private const val mockWebServer: String =
        "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
    private const val coroutinesTest: String =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Coroutines.Version.coroutines}"

    const val robolectric: String = "org.robolectric:robolectric:${Version.robolectric}"

    const val testCore: String = "androidx.test:core:${Version.androidxTest}"
    const val testExt: String = "androidx.test.ext:junit:${Version.testExt}"
    const val testRunner: String = "androidx.test:runner:${Version.androidxTest}"
    const val testRules: String = "androidx.test:rules:${Version.androidxTest}"
    const val mockkAndroid: String = "io.mockk:mockk-android:${Version.mockk}"
    const val fragmentTesting: String = "androidx.fragment:fragment-testing:${Version.fragment}"
    const val espresso: String = "androidx.test.espresso:espresso-core:${Version.espresso}"

    val unitTest = listOf(
        junit, archTest, truth, mockk, mockWebServer, coroutinesTest
    )
}