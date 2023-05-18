package com.gb.vale.mobilechallenget.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gb.vale.mobilechallenget.presentation.detail.DetailScreen
import com.gb.vale.mobilechallenget.presentation.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        route = ROOT_GRAPH_ROUTE) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                hiltViewModel(),
                navController = navController
            )
        }

        composable(route = Screen.DetailScreen.route +"/{recipes}",
            arguments = listOf(
                navArgument("recipes") { type = NavType.StringType },
            )) {
          DetailScreen(hiltViewModel(),navController, it.arguments?.getString("recipes")?:"")
        }

    }

}