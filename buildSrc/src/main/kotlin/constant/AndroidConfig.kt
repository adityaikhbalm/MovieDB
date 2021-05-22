package constant

object AndroidConfig {
    object Version {
        private const val versionMajor: Int = 1
        private const val versionMinor: Int = 0
        private const val versionPatch: Int = 0

        const val versionCode: Int = versionMajor * 100 + versionMinor * 10 + versionPatch
        const val versionName: String = "${versionMajor}.${versionMinor}.${versionPatch}"

        const val compileSdkVersion: Int = 30
        const val targetSdkVersion: Int = 30
        const val minSdkVersion: Int = 21
    }

    object Android {
        const val applicationId: String = "com.adityaikhbalm.moviedb"
        const val testInstrumentationRunner: String = "androidx.test.runner.AndroidJUnitRunner"
    }
}

interface BuildType {
    companion object {
        const val DEBUG: String = "debug"
        const val RELEASE: String = "release"
    }

    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled: Boolean = false
    override val isTestCoverageEnabled: Boolean = true

    const val applicationIdSuffix: String = ".debug"
    const val versionNameSuffix: String = "-DEBUG"
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled: Boolean = true
    override val isTestCoverageEnabled: Boolean = false
}