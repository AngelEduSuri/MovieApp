package com.aesuriagasalazar.movieapp.framework.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.framework.navigation.NavRoutes.DetailScreen
import com.aesuriagasalazar.movieapp.framework.navigation.NavRoutes.HomeScreen
import com.aesuriagasalazar.movieapp.framework.ui.components.MovieDrawerContent
import com.aesuriagasalazar.movieapp.framework.ui.screens.details.MovieScreen
import com.aesuriagasalazar.movieapp.framework.ui.screens.main.MainScreen
import kotlinx.coroutines.launch

@Composable
fun MovieAppNavigation() {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        scaffoldState = scaffoldState,
        drawerContent = {
            MovieDrawerContent(
                listOf(
                    Genre(1, "Action"),
                    Genre(2, "Adventure"),
                    Genre(3, "Thriller"),
                    Genre(4, "Horror"),
                    Genre(5, "Mystery")
                )
            )
        },
        drawerContentColor = MaterialTheme.colors.onBackground
    ) {
        NavHost(
            modifier = Modifier.padding(paddingValues = it),
            navController = navController,
            startDestination = HomeScreen.route
        ) {
            composable(route = HomeScreen.route) {
                MainScreen(onClickDrawer = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                })
            }
            composable(route = DetailScreen.route) {
                MovieScreen()
            }
        }
    }
}