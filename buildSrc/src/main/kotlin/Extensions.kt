import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.apply
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.androidApplication: PluginDependencySpec
    get() = id("com.android.application")

val PluginDependenciesSpec.androidLibrary: PluginDependencySpec
    get() = id("com.android.library")

val PluginDependenciesSpec.kotlin: PluginDependencySpec
    get() = id("kotlin")

val PluginDependenciesSpec.kotlinKapt: PluginDependencySpec
    get() = id("kotlin-kapt")

val Project.applySpotless
    get() = apply(plugin = "spotless")

val PluginDependenciesSpec.kotlinLibrary: PluginDependencySpec
    get() = id("kotlin-library")

val PluginDependenciesSpec.baseGradlePlugin: PluginDependencySpec
    get() = id("base-gradle-plugin")

val Project.jacocoTestReport
    get() = apply(from = "$rootDir/jacocoreport.gradle.kts")

fun RepositoryHandler.maven(url: String) {
    maven {
        setUrl(url)
    }
}

fun RepositoryHandler.applyDefault() {
    google()
    mavenCentral()
}

fun DependencyHandler.unitTestingAll(list: List<String>) {
    list.forEach {
        add("testImplementation", it)
    }
}

fun DependencyHandler.implementAll(list: List<String>) {
    list.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)