package com.adityaikhbalm.core.cache

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adityaikhbalm.core.model.mapper.CastMapper
import com.adityaikhbalm.core.model.mapper.FavoriteMapper
import com.adityaikhbalm.core.model.mapper.SimilarMapper
import com.adityaikhbalm.core.model.mapper.TrailerMapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
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
        CoroutineScope(Job()).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                movieCacheImpl.insertFavorite(SampleData.favorite)

                val data = movieCacheImpl.getFavorite(SampleData.favorite.id)
                data.collect {
                    Assert.assertEquals(it.favorite?.id, SampleData.favorite.id)
                }
            }
        }
    }

    @Test
    fun deleteFavorite() {
        CoroutineScope(Job()).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                movieCacheImpl.insertFavorite(SampleData.favorite)
                movieCacheImpl.deleteFavorite(SampleData.favorite)

                val data = movieCacheImpl.getFavorite(SampleData.favorite.id)
                data.collect {
                    Assert.assertNotEquals(it.favorite?.id, SampleData.favorite.id)
                }
            }
        }
    }

    @Test
    fun selectFavorite() {
        CoroutineScope(Job()).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                movieCacheImpl.insertFavorite(SampleData.favorite)

                val data = movieCacheImpl.getFavorite(SampleData.favorite.id)
                data.collect {
                    Assert.assertEquals(it.favorite?.title, SampleData.favorite.title)
                }
            }
        }
    }
}
