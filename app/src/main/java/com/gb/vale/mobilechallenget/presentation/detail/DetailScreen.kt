package com.gb.vale.mobilechallenget.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gb.vale.mobilechallenget.R
import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.presentation.MainViewModel
import com.gb.vale.mobilechallenget.presentation.home.RecipeEvent
import com.gb.vale.mobilechallenget.presentation.home.RecipeUiEvent
import com.gb.vale.mobilechallenget.ui.theme.navigation.Screen
import com.gb.vale.mobilechallenget.utils.parseStringGson
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(viewModel: MainViewModel, navController: NavController,recipeModel : String) {

    val recipe : RecipeModel = parseStringGson(recipeModel)

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is RecipeUiEvent.NavigateToMap -> {
                    navController.navigate(Screen.DetailScreen.route)
                }

                is RecipeUiEvent.NavigateToBackHome -> {
                    navController.popBackStack()
                }
                else -> {}
            }
        }
    }

    Scaffold(
        topBar = { TopAppBarDetail(viewModel) }) {
        Column(modifier = Modifier.imePadding()) {
            AvatarRecipe()
            Text(
                modifier = Modifier.padding(20.dp),
                text = recipe.title,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier.padding(20.dp),
                text = recipe.description,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Button( modifier = Modifier.padding(40.dp),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.green)),
                onClick = {
            }) {
                Text(text = "Ir al mapa")
            }

        }
    }
}

@Composable
fun TopAppBarDetail(
    viewModel: MainViewModel
) {
    Box {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = colorResource(R.color.green),
            title = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text(
                            stringResource(R.string.text_title_detail_recipes),
                            color = Color.White
                        )
                    }
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    viewModel.onEvent(RecipeEvent.BackHomeRecipes)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        )
    }
}


@Composable
fun AvatarRecipe(modifier: Modifier = Modifier.height(50.dp).fillMaxWidth()) {
    AsyncImage(
        model = "https://loremflickr.com/400/400/cat?lock=1",
        contentDescription = null,
        modifier  = modifier
    )
}