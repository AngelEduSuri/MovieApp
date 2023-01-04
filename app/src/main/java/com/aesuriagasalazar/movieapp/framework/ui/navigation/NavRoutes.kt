package com.aesuriagasalazar.movieapp.framework.ui.navigation

sealed class NavRoutes(val route: String) {
    object SplashScreen: NavRoutes(route = "splash")
    object HomeScreen: NavRoutes(route = "home")
    object DetailScreen: NavRoutes(route = "details")
}
