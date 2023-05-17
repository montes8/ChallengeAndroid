package com.gb.vale.mobilechallenget.ui.theme.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gb.vale.mobilechallenget.presentation.home.HomeScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {

    navigation(startDestination = Screen.HomeScreen.route, route = AUTH_GRAPH_ROUTE){

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(hiltViewModel(), navController = navController)
        }

    }
}