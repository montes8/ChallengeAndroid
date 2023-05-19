package com.gb.vale.mobilechallenget.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gb.vale.mobilechallenget.R
import com.gb.vale.mobilechallenget.ui.theme.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(viewModel: DetailViewModel, navController: NavController,recipeModel : String) {

    viewModel.idDetail = recipeModel.toLong()
    val scroll = rememberScrollState(0)

    Scaffold(
        topBar = { TopAppBarDetail(navController) }) {
            Column(modifier = Modifier.imePadding()) {
                AvatarRecipe(viewModel.uiStateDetail.recipeModel.urlImg)
                Row(modifier = Modifier.fillMaxWidth().padding(20.dp)){

                        Text(
                            modifier = Modifier.weight(2f),
                            text = viewModel.uiStateDetail.recipeModel.title,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )

                        Button( modifier = Modifier.weight(1f).height(35.dp),
                            shape = RoundedCornerShape(20.dp),
                            border = BorderStroke(1.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.green)),
                            onClick = {
                                navController.navigate(Screen.MapScreen.route)
                            }) {
                            Text(text = "Mapa"
                            )
                        }

                }

                Text(
                    modifier = Modifier.padding(top = 20.dp
                    , start = 20.dp, end = 20.dp),
                    text = "Preparación",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier.padding(20.dp).verticalScroll(scroll),
                    text = viewModel.uiStateDetail.recipeModel.preparation,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis,

                )

            }
        }

}

@Composable
fun TopAppBarDetail(
    navController: NavController
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
                    navController.popBackStack()
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
fun AvatarRecipe(url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier  = Modifier.fillMaxWidth()
    )
}