package com.adityaikhbalm.core.remote.service

import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.core.model.response.MovieResponse
import com.adityaikhbalm.libraries.utility.Constant.api_key
import com.adityaikhbalm.libraries.utility.Constant.api_name
import com.adityaikhbalm.libraries.utility.Constant.base_url
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    companion object {
        operator fun invoke(): MovieApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter(api_name, api_key)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(base_url)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(MovieApiService::class.java)
        }
    }

    @GET("discover/movie?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getBannerMovie(): MovieResponse

    @GET("movie/popular?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false")
    suspend fun getPopularMovie(
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/upcoming?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getComingMovie(): MovieResponse

    @GET("movie/{id}?append_to_response=credits,videos,similar")
    suspend fun getDetailMovie(
        @Path("id") id: Int
    ): Movie

    @GET("search/movie?language=en-US&include_adult=false")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResponse
}
