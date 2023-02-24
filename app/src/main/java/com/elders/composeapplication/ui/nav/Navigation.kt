package com.elders.composeapplication.ui.nav

import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.elders.composeapplication.ui.screens.DetailScreen
import com.elders.composeapplication.ui.screens.HomeScreen
import com.elders.composeapplication.ui.vm.GameViewModel
import com.elders.composeapplication.utils.Constants.Companion.KEY_GAME_ID
import com.elders.composeapplication.utils.Constants.Screens.DETAIL_SCREEN
import com.elders.composeapplication.utils.Constants.Screens.HOME_SCREEN

sealed class Screens(val route: String) {
    object Home : Screens(route = HOME_SCREEN)
    object Detail : Screens(route = DETAIL_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, gameViewModel: GameViewModel) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {

        composable(route = Screens.Home.route) {

            HomeScreen(gameViewModel = gameViewModel, navController = navController)
        }
        composable(route = Screens.Detail.route+ "/{$KEY_GAME_ID}") { BackStackEntry ->
            DetailScreen( navController = navController)
        }
    }
}