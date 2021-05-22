package com.adityaikhbalm.core.remote

import com.adityaikhbalm.core.remote.helper.MovieRequestDispatcher
import com.adityaikhbalm.core.remote.service.MovieApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

open class BaseTest {
    private lateinit var mockWebServer: MockWebServer
    lateinit var movieApiService: MovieApiService
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var loggingInterceptor: HttpLoggingInterceptor

    @Before
    open fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MovieRequestDispatcher()
        mockWebServer.start()

        loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        movieApiService = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MovieApiService::class.java)
    }
}
