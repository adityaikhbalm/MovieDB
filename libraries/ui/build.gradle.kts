import constant.LibraryDependency.AppComponent
import constant.LibraryDependency.ImageLoader
import constant.LibraryDependency.View
import constant.ModulesDependency

plugins {
    androidLibrary
    baseGradlePlugin
}

dependencies {
    implementation(project(ModulesDependency.librariesUtility))

    implementation(AppComponent.coreKtx)
    implementAll(View.components)
    implementAll(ImageLoader.components)
}
