import constant.LibraryDependency.AppComponent
import constant.LibraryDependency.ArchitectureComponent
import constant.LibraryDependency.DI
import constant.LibraryDependency.ImageLoader
import constant.LibraryDependency.Paging
import constant.LibraryDependency.View
import constant.ModulesDependency

plugins {
    androidLibrary
    baseGradlePlugin
}

dependencies {
    implementation(project(ModulesDependency.coreDomain))
    implementation(project(ModulesDependency.featureDetail))

    implementAll(AppComponent.components)
    implementAll(ArchitectureComponent.components)
    implementAll(ImageLoader.components)
    implementAll(View.components)

    implementation(Paging.paging)
    implementation(DI.koin)
}
