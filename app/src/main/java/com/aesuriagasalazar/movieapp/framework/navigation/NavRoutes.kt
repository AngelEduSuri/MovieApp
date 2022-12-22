package com.aesuriagasalazar.movieapp.framework.navigation

sealed class NavRoutes(val route: String) {
    object HomeScreen: NavRoutes(route = "home")
    object DetailScreen: NavRoutes(route = "details")
}
