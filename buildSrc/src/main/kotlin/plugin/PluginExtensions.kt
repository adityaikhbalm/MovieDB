package plugin

import com.android.build.gradle.BaseExtension
import constant.AndroidConfig
import constant.BuildType
import constant.BuildTypeDebug
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureDefaultPlugins() {
    plugins.apply("kotlin-android")
}

internal fun Project.configureAndroidApp() =  this.extensions.getByType<BaseExtension>().run {
    compileSdkVersion(AndroidConfig.Version.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidConfig.Version.minSdkVersion)
        targetSdkVersion(AndroidConfig.Version.targetSdkVersion)
        testInstrumentationRunner = AndroidConfig.Android.testInstrumentationRunner
    }
    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    project.tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    buildFeatures.viewBinding = true
}