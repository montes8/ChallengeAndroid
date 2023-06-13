package com.gb.vale.mobilechallenget.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gb.vale.mobilechallenget.ui.detail.DetailScreen
import com.gb.vale.mobilechallenget.ui.home.HomeScreen
import com.gb.vale.mobilechallenget.ui.home.HomeViewModel
import com.gb.vale.mobilechallenget.ui.map.MapScreen

@Composable
fun Navigation(homeViewModel : HomeViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        route = ROOT_GRAPH_ROUTE) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                homeViewModel,
                navController = navController
            )
        }

        composable(route = Screen.DetailScreen.route +"/{idRecipes}",
            arguments = listOf(
                navArgument("idRecipes") { type = NavType.StringType },
            )) {
          DetailScreen(homeViewModel,navController, it.arguments?.getString("idRecipes")?:"")
        }

        composable(route = Screen.MapScreen.route) { MapScreen(hiltViewModel())}
    }

}