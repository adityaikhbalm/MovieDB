package constant

interface Libraries {
    val components: List<String>
}

object LibraryDependency {
    object ArchitectureComponent : Libraries {
        object Version {
            const val lifeCycle: String = "2.3.1"
        }

        private const val lifeCycle: String =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifeCycle}"
        private const val liveData: String =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifeCycle}"
        private const val viewModel: String =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycle}"
        private const val common: String =
            "androidx.lifecycle:lifecycle-common-java8:${Version.lifeCycle}"

        override val components: List<String>
            get() = listOf(lifeCycle, liveData, viewModel, common)
    }

    object AppComponent : Libraries {
        object Version {
            const val appCompat: String = "1.3.0-rc01"
            const val activity: String = "1.3.0-alpha07"
            const val coreKtx: String = "1.5.0-rc02"
            const val fragment: String = "1.3.2"
        }

        const val coreKtx: String = "androidx.core:core-ktx:${Version.coreKtx}"
        private const val appCompat: String = "androidx.appcompat:appcompat:${Version.appCompat}"
        private const val activity: String = "androidx.activity:activity-ktx:${Version.activity}"
        private const val fragment: String = "androidx.fragment:fragment-ktx:${Version.fragment}"

        override val components: List<String>
            get() = listOf(appCompat, activity, fragment)
    }

    object Navigation : Libraries {
        object Version {
            const val navigation: String = "2.3.5"
        }

        private const val navigationFragment: String =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        private const val navigationUi: String =
            "androidx.navigation:navigation-ui-ktx:${Version.navigation}"

        override val components: List<String>
            get() = listOf(navigationFragment, navigationUi)
    }

    object Coroutines : Libraries {
        object Version {
            const val coroutines: String = "1.5.0"
        }

        const val core: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        private const val android: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        override val components: List<String> = listOf(core, android)
    }

    object DI {
        object Version {
            const val koin: String = "3.0.2"
        }

        const val core: String = "io.insert-koin:koin-core:${Version.koin}"
        const val koin: String = "io.insert-koin:koin-android:${Version.koin}"
    }

    object Network : Libraries {
        object Version {
            const val okHttp: String = "4.9.1"
            const val retrofit: String = "2.9.0"
            const val moshi: String = "1.12.0"
        }

        object AnnotationProcessor {
            const val moshi: String = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
        }

        private const val okHttp: String = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
        private const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"
        private const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitMoshi: String =
            "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
        const val moshi: String = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"

        override val components: List<String> = listOf(
            okHttp, loggingInterceptor, retrofit, retrofitMoshi, moshi
        )
    }

    object View : Libraries {
        object Version {
            const val constraintLayout: String = "2.0.4"
            const val material: String = "1.3.0"
            const val recyclerView: String = "1.2.0"
            const val indicator: String = "1.4"
        }

        private const val constraintLayout: String =
            "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val material: String =
            "com.google.android.material:material:${Version.material}"
        private const val recyclerview: String =
            "androidx.recyclerview:recyclerview:${Version.recyclerView}"
        const val indicator: String =
            "com.github.wching:Android-Indefinite-Pager-Indicator:${Version.indicator}"

        override val components: List<String>
            get() = listOf(constraintLayout, material, recyclerview)
    }

    object Database : Libraries {
        object Version {
            const val room: String = "2.3.0"
        }

        private const val roomRuntime: String = "androidx.room:room-runtime:${Version.room}"
        private const val roomKtx: String = "androidx.room:room-ktx:${Version.room}"
        const val roomCommon: String = "androidx.room:room-common:${Version.room}"

        object AnnotationProcessor {
            const val roomKapt: String = "androidx.room:room-compiler:${Version.room}"
        }

        override val components: List<String>
            get() = listOf(roomRuntime, roomKtx)
    }

    object Paging {
        object Version {
            const val paging: String = "3.0.0"
        }

        const val paging: String = "androidx.paging:paging-runtime:${Version.paging}"
    }

    object ImageLoader : Libraries {
        object Version {
            const val glide: String = "4.12.0"
            const val shimmer: String = "0.5.0"
            const val circle: String = "1.5"
        }

        private const val glide: String = "com.github.bumptech.glide:glide:${Version.glide}"
        private const val shimmer: String = "com.facebook.shimmer:shimmer:${Version.shimmer}"
        private const val circle: String =
            "com.github.abdularis:circularimageview:${Version.circle}"

        override val components: List<String>
            get() = listOf(glide, shimmer, circle)
    }
}