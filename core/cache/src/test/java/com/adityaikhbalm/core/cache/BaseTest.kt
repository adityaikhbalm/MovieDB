package com.adityaikhbalm.core.cache

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before

open class BaseTest {
    lateinit var db: MovieDatabase

    @Before
    open fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()
    }

    @After
    fun tearDown() {
        runBlocking(Dispatchers.IO) {
            db.clearAllTables()
        }

        db.close()
    }
}
