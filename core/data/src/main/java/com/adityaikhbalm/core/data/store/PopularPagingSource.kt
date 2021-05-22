package com.adityaikhbalm.core.data.store

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adityaikhbalm.core.data.MovieDataRepository
import com.adityaikhbalm.core.model.response.Movie
import com.adityaikhbalm.libraries.utility.Constant
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException

class PopularPagingSource(
    private val movieRepository: MovieDataRepository,
    private val type: Int,
    private val query: String = "default"
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: Constant.page

            var data = if (type == Constant.popular_type) movieRepository.getPagingPopular(page)
            else movieRepository.searchMovie(query, page)

            if (type == Constant.search_type && page == Constant.min_page ||
                type == Constant.popular_type && page == Constant.max_page
            ) {
                data = emptyList()
            }

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (data.isNullOrEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: SocketTimeoutException) {
            LoadResult.Error(e)
        } catch (e: SocketException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
