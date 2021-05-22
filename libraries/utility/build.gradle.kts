import constant.LibraryDependency.AppComponent

plugins {
    androidLibrary
    baseGradlePlugin
}

dependencies {
    implementation(AppComponent.coreKtx)
}
