package com.adityaikhbalm.core.cache

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class BaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var db: MovieDatabase

    @Before
    open fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()
    }

    @After
    fun tearDown() {
        db.close()
    }
}
