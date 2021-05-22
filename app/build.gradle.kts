import constant.AndroidConfig.Android
import constant.AndroidConfig.Version
import constant.LibraryDependency.DI
import constant.LibraryDependency.Navigation
import constant.LibraryDependency.View
import constant.ModulesDependency

plugins {
    androidApplication
    baseGradlePlugin
}

android {
    defaultConfig {
        applicationId = Android.applicationId
        versionCode = Version.versionCode
        versionName = Version.versionName
    }
}

dependencies {
    implementation(project(ModulesDependency.librariesAbstraction))
    implementation(project(ModulesDependency.librariesUi))
    implementation(project(ModulesDependency.librariesUtility))

    implementAll(Navigation.components)
    implementAll(View.components)

    implementation(DI.koin)

    implementation(project(ModulesDependency.coreData))
    implementation(project(ModulesDependency.coreDomain))
    implementation(project(ModulesDependency.coreRemote))
    implementation(project(ModulesDependency.coreCache))
    implementation(project(ModulesDependency.featureHome))
    implementation(project(ModulesDependency.featurePopular))
    implementation(project(ModulesDependency.featureFavorite))
    implementation(project(ModulesDependency.featureDetail))
}
