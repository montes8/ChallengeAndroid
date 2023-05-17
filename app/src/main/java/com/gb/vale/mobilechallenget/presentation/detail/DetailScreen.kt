package com.gb.vale.mobilechallenget.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gb.vale.mobilechallenget.presentation.MainViewModel
import com.gb.vale.mobilechallenget.presentation.home.RecipeUiEvent
import com.gb.vale.mobilechallenget.ui.theme.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(viewModel: MainViewModel, navController: NavController) {

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is RecipeUiEvent.NavigateToMap -> {
                    navController.navigate(Screen.DetailScreen.route)
                }

                is RecipeUiEvent.NavigateToDetail -> {
                }
            }
        }
    }

    Column( modifier = Modifier.padding(20.dp)
    ){

    }
}