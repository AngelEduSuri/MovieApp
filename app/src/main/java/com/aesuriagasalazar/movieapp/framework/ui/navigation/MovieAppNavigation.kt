package com.aesuriagasalazar.movieapp.framework.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aesuriagasalazar.movieapp.framework.ui.components.MovieDrawerContent
import com.aesuriagasalazar.movieapp.framework.ui.navigation.NavRoutes.DetailScreen
import com.aesuriagasalazar.movieapp.framework.ui.navigation.NavRoutes.HomeScreen
import com.aesuriagasalazar.movieapp.framework.ui.screens.details.MovieScreen
import com.aesuriagasalazar.movieapp.framework.ui.screens.main.MainScreen
import com.aesuriagasalazar.movieapp.framework.ui.screens.main.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieAppNavigation(viewModel: MainViewModel = koinViewModel()) {

    val uiState = viewModel.uiState.collectAsState().value
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        scaffoldState = scaffoldState,
        drawerContent = {
            MovieDrawerContent(
                currentGenre = uiState.currentGenre,
                onGenreClicked = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    viewModel.onGenreSelected(it)
                },
                genreState = uiState.dataResponseGenres
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
                MainScreen(
                    moviesState = uiState.dataResponsePopularMovies,
                    currentGenre = uiState.currentGenre,
                    isGridViewEnabled = uiState.isGridView,
                    onClickDrawer = {
                        scope.launch {
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    onClickView = viewModel::onGridViewClicked
                )
            }
            composable(route = DetailScreen.route) {
                MovieScreen()
            }
        }
    }
}