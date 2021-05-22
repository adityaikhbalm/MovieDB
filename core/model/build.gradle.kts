import constant.LibraryDependency.Database
import constant.LibraryDependency.Network

plugins {
    kotlinLibrary
    kotlinKapt
}

dependencies {
    implementation(Network.retrofitMoshi)
    implementation(Database.roomCommon)
    implementation(Network.moshi)
    kapt(Network.AnnotationProcessor.moshi)
}
