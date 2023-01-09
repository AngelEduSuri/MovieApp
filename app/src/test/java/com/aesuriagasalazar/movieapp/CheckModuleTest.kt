@file:OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package com.aesuriagasalazar.movieapp

import com.aesuriagasalazar.movieapp.framework.modulesprovider.parentMovieDetailsModule
import com.aesuriagasalazar.movieapp.framework.modulesprovider.parentMoviesModule
import com.aesuriagasalazar.movieapp.framework.modulesprovider.retrofitModule
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class CheckModuleTest: KoinTest {

    private val dispatcher = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        dispatcher
        Dispatchers.setMain(dispatcher)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create {
        Mockito.mock(it.java)
    }

    @Test
    fun `check all modules`() = checkModules {
        modules(parentMoviesModule, parentMovieDetailsModule, retrofitModule)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.close()
    }
}