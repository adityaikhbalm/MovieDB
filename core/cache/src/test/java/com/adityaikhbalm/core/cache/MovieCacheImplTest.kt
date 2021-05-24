package com.adityaikhbalm.core.cache

import android.os.Build
import androidx.paging.PagingSource.LoadParams.Refresh
import androidx.paging.PagingSource.LoadResult.Page
import com.adityaikhbalm.core.model.mapper.CastMapper
import com.adityaikhbalm.core.model.mapper.FavoriteMapper
import com.adityaikhbalm.core.model.mapper.SimilarMapper
import com.adityaikhbalm.core.model.mapper.TrailerMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class MovieCacheImplTest : BaseTest() {

    private lateinit var movieCacheImpl: MovieCacheImpl

    @Before
    override fun setup() {
        super.setup()
        movieCacheImpl = MovieCacheImpl(
            db, FavoriteMapper(), CastMapper(), TrailerMapper(), SimilarMapper()
        )
    }

    @Test
    fun insertFavorite() {
        runBlocking(Dispatchers.IO) {
            movieCacheImpl.insertFavorite(SampleData.favorite)

            val data = movieCacheImpl.getFavorite(SampleData.favorite.id).first()
            Assert.assertEquals(data.favorite?.id, SampleData.favorite.id)
        }
    }

    @Test
    fun deleteFavorite() {
        runBlocking(Dispatchers.IO) {
            movieCacheImpl.insertFavorite(SampleData.favorite)
            movieCacheImpl.deleteFavorite(SampleData.favorite)

            val data = movieCacheImpl.getFavorite(SampleData.favorite.id).first()
            Assert.assertNull(data)
        }
    }

    @Test
    fun selectFavorite() {
        runBlocking(Dispatchers.IO) {
            movieCacheImpl.insertFavorite(SampleData.favorite)

            val data = movieCacheImpl.getFavorite(SampleData.favorite.id).first()
            Assert.assertEquals(data.favorite?.title, SampleData.favorite.title)
        }
    }

    @Test
    fun searchFavorite() {
        runBlocking(Dispatchers.IO) {
            val data = listOf(
                FavoriteMapper().mapToEntity(SampleData.favorite),
                FavoriteMapper().mapToEntity(SampleData.favorite2)
            )

            movieCacheImpl.insertFavorite(SampleData.favorite)
            movieCacheImpl.insertFavorite(SampleData.favorite2)

            Assert.assertEquals(
                Page(
                    data = data,
                    prevKey = null,
                    nextKey = null,
                    itemsAfter = 0,
                    itemsBefore = 0
                ),
                movieCacheImpl.searchFavorite("avenger").load(
                    Refresh(
                        key = null,
                        loadSize = 2,
                        placeholdersEnabled = false
                    )
                )
            )
        }
    }

    @Test
    fun selectAllFavorite() {
        runBlocking(Dispatchers.IO) {
            val data = listOf(
                FavoriteMapper().mapToEntity(SampleData.favorite),
                FavoriteMapper().mapToEntity(SampleData.favorite2)
            )

            movieCacheImpl.insertFavorite(SampleData.favorite)
            movieCacheImpl.insertFavorite(SampleData.favorite2)

            Assert.assertEquals(
                Page(
                    data = data,
                    prevKey = null,
                    nextKey = null,
                    itemsAfter = 0,
                    itemsBefore = 0
                ),
                movieCacheImpl.getAllFavorite().load(
                    Refresh(
                        key = null,
                        loadSize = 2,
                        placeholdersEnabled = false
                    )
                )
            )
        }
    }
}
