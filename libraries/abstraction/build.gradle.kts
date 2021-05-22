import constant.LibraryDependency.ArchitectureComponent
import constant.LibraryDependency.AppComponent
import constant.LibraryDependency.ImageLoader
import constant.LibraryDependency.Coroutines
import constant.LibraryDependency.View
import constant.ModulesDependency

plugins {
    androidLibrary
    baseGradlePlugin
}

dependencies {
    implementation(project(ModulesDependency.coreModel))
    implementation(project(ModulesDependency.librariesUi))
    implementation(project(ModulesDependency.librariesUtility))

    implementAll(ArchitectureComponent.components)
    implementAll(AppComponent.components)
    implementAll(ImageLoader.components)
    implementation(Coroutines.core)
    implementation(View.material)
}
