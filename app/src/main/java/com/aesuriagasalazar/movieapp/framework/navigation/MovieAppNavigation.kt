package com.aesuriagasalazar.movieapp.framework.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aesuriagasalazar.movieapp.framework.navigation.NavRoutes.*
import com.aesuriagasalazar.movieapp.framework.ui.screens.details.MovieScreen
import com.aesuriagasalazar.movieapp.framework.ui.screens.main.MainScreen

@Composable
fun MovieAppNavigation() {

    val navController = rememberNavController()

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        NavHost(navController = navController, startDestination = HomeScreen.route) {
            composable(route = HomeScreen.route) {
                MainScreen()
            }
            composable(route = DetailScreen.route) {
                MovieScreen()
            }
        }
    }
}