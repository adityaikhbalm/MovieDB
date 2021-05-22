import constant.LibraryDependency.Coroutines
import constant.LibraryDependency.DI
import constant.LibraryDependency.Paging
import constant.ModulesDependency

plugins {
    androidLibrary
    baseGradlePlugin
}

dependencies {
    implementation(project(ModulesDependency.coreDomain))
    implementation(project(ModulesDependency.coreModel))
    implementation(project(ModulesDependency.librariesAbstraction))
    implementation(project(ModulesDependency.librariesUtility))

    implementation(Coroutines.core)
    implementation(DI.core)
    implementation(Paging.paging)
}
