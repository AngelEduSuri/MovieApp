package com.aesuriagasalazar.movieapp

import com.aesuriagasalazar.movieapp.framework.modulesprovider.movieModule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class CheckModuleTest: KoinTest {
    @get:Rule
    val mockProvider = MockProviderRule.create {
        Mockito.mock(it.java)
    }

    @Test
    fun `check all modules`() = checkModules {
        modules(movieModule)
    }
}