import constant.LibraryDependency.Coroutines
import constant.LibraryDependency.DI
import constant.LibraryDependency.Paging
import constant.ModulesDependency

plugins {
    androidLibrary
    baseGradlePlugin
}

dependencies {
    api(project(ModulesDependency.coreModel))
    api(project(ModulesDependency.librariesAbstraction))
    api(project(ModulesDependency.librariesUtility))

    implementation(Coroutines.core)
    implementation(DI.core)
    implementation(Paging.paging)
}
