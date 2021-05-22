import constant.LibraryDependency.Coroutines
import constant.LibraryDependency.DI
import constant.LibraryDependency.Network
import constant.ModulesDependency
import constant.TestLibraryDependency

plugins {
    androidLibrary
    baseGradlePlugin
    kotlinKapt
}

dependencies {
    implementation(project(ModulesDependency.coreData))
    implementation(project(ModulesDependency.coreModel))
    implementation(project(ModulesDependency.librariesUtility))

    implementation(Coroutines.core)
    implementation(DI.core)
    implementAll(Network.components)
    kapt(Network.AnnotationProcessor.moshi)

    unitTestingAll(TestLibraryDependency.unitTest)
}
