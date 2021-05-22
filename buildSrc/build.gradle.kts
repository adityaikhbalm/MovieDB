import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("base-gradle-plugin") {
            id = "base-gradle-plugin"
            implementationClass = "plugin.BaseGradlePlugin"
        }
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = Plugin.Version.kotlin
}

object Plugin {
    object Version {
        const val androidGradle: String = "4.2.1"
        const val kotlin: String = "1.5.0"
        const val spotless: String = "5.12.5"
    }

    const val androidGradle: String = "com.android.tools.build:gradle:${Version.androidGradle}"
    const val kotlin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val spotless: String = "com.diffplug.spotless:spotless-plugin-gradle:${Version.spotless}"
}

dependencies {
    implementation(Plugin.androidGradle)
    implementation(Plugin.kotlin)
    implementation(Plugin.spotless)
}

tasks.named("cleanTest") {
    group = "verification"
}