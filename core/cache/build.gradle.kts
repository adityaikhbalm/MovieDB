import constant.LibraryDependency.Coroutines
import constant.LibraryDependency.DI
import constant.LibraryDependency.Database
import constant.LibraryDependency.Paging
import constant.ModulesDependency
import constant.TestLibraryDependency

plugins {
    androidLibrary
    baseGradlePlugin
    kotlinKapt
}

android {
    testOptions {
        unitTests.all {
            val d: MutableMap<String, Any> = mutableMapOf()
            d.plus(Pair("robolectric.dependency.repo.url", "https://repo1.maven.org/maven"))
            it.systemProperties = d
        }
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(ModulesDependency.coreData))
    implementation(project(ModulesDependency.coreModel))

    implementation(Coroutines.core)
    implementation(DI.core)
    implementation(Paging.paging)
    implementAll(Database.components)
    kapt(Database.AnnotationProcessor.roomKapt)

    unitTestingAll(TestLibraryDependency.unitTest)
    testImplementation(TestLibraryDependency.robolectric)
    testImplementation(TestLibraryDependency.roomTest)
    testImplementation(TestLibraryDependency.testCore)
}
