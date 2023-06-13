package com.gb.vale.mobilechallenget.ui.home

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gb.vale.mobilechallenget.R
import com.gb.vale.mobilechallenget.components.CircleAvatar
import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.ui.theme.navigation.Screen
import com.gb.vale.mobilechallenget.utils.EMPTY
import com.gb.vale.mobilechallenget.utils.parseFromString

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val handler = Handler(Looper.getMainLooper())
    val runnable = Runnable { viewModel.listFilter() }
    Box{
        Column( modifier = Modifier.padding(20.dp)){
            SearchRecipes(viewModel)
            if (!viewModel.uiState.filter)ListInitialRecipes(viewModel,navController)
            viewModel.uiState = viewModel.uiState.copy(filter = viewModel.uiState.searchQuery.isNotEmpty())
            if (viewModel.uiState.searchQuery.isNotEmpty()) {

                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 150)
                ListSearchRecipes(viewModel,navController)
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
private fun SearchRecipes(
    viewModel: HomeViewModel
) {
    val focusRequester = FocusRequester()

        TopAppBar(modifier = Modifier
            .fillMaxWidth(),
            backgroundColor = Color.White,
            title = {
                BasicTextField(
                    modifier = Modifier
                        .wrapContentHeight()
                        .focusRequester(focusRequester) ,
                    value = viewModel.uiState.searchQuery,
                    cursorBrush = SolidColor(MaterialTheme.colors.primary),
                    onValueChange = {
                        viewModel.floatingButton = false
                        viewModel.uiState = viewModel.uiState.copy(searchQuery = it)
                    }
                )
            },
            actions = {
                IconButton(
                    modifier = Modifier.padding(start = 10.dp), onClick = {
                        viewModel.uiState = viewModel.uiState.copy(searchQuery = EMPTY)
                    }) {
                    Icon(
                        rememberVectorPainter(Icons.Sharp.Close),
                        contentDescription = null, tint = Color.Gray
                    )
                }

            }
        )
}


@Composable
fun ListInitialRecipes(
    viewModel: HomeViewModel, navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (viewModel.uiState.recipes.isNotEmpty()) {

            Text(
                text = stringResource(id = R.string.text_title_recipes),
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                color = colorResource(id = R.color.gray_600)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(viewModel.uiState.recipes) { recipe ->
                    RecipesItem(
                        recipe = recipe,
                        openNewChatAction = {
                            viewModel.floatingButton = false
                            navController.navigate(Screen.DetailScreen.withArgs(
                                parseFromString(recipe)))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RecipesItem(
    recipe: RecipeModel,
    openNewChatAction: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { openNewChatAction() }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleAvatar(image = recipe.urlImg)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.55f),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }

}

@Composable
fun ListSearchRecipes(viewModel: HomeViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (viewModel.uiState.recipesFilter.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.text_title_recipes),
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                color = colorResource(id = R.color.gray_600)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(viewModel.uiState.recipesFilter) { recipe ->
                    RecipesItem(
                        recipe = recipe,
                        openNewChatAction = {
                            navController.navigate(Screen.DetailScreen.withArgs(
                                parseFromString(recipe)))
                        }
                    )
                }
            }
        }
    }
}
