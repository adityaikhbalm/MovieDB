package com.adityaikhbalm.core.remote.helper

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class MovieRequestDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        println(Constant.movie_search)
        println(request.path)
        return when (request.path) {
            Constant.movie -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("movie.json"))
            }
            Constant.movie_detail -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("movie_detail.json"))
            }
            Constant.movie_search -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("movie_search.json"))
            }
            else -> MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
        }
    }

    private fun getJson(path: String): String {
        val uri = javaClass.classLoader.getResourceAsStream(path)
        return String(uri.readBytes())
    }
}
