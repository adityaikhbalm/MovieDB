package com.adityaikhbalm.core.remote

import com.adityaikhbalm.core.data.repository.MovieRemote
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test

internal class MovieRemoteImplTest : BaseTest() {

    private lateinit var movieRemote: MovieRemote

    override fun setup() {
        super.setup()
        movieRemote = MovieRemoteImpl(movieApiService)
    }

    @Test
    fun getMovie() {
        runBlocking(Dispatchers.IO) {
            val data = movieRemote.getComingMovie()

            Assert.assertEquals(data.page, 1)
            Truth.assertThat(data.results).isNotEmpty()
            Truth.assertThat(data.total_pages).isAtLeast(1)
        }
    }

    @Test
    fun getDetailMovie() {
        runBlocking(Dispatchers.IO) {
            val data = movieRemote.getDetailMovie(1771)

            Assert.assertEquals(data.id, 1771)
        }
    }

    @Test
    fun searchMovie() {
        runBlocking(Dispatchers.IO) {
            val data = movieRemote.searchMovie("avenger", 1)

            Assert.assertEquals(data.page, 1)
            Truth.assertThat(data.results).isNotEmpty()
            Truth.assertThat(data.total_pages).isAtLeast(1)
            MatcherAssert.assertThat(
                data.results!![0].title?.lowercase(), containsString("avenger")
            )
        }
    }
}
