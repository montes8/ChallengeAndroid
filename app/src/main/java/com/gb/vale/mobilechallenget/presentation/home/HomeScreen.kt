package com.gb.vale.mobilechallenget.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gb.vale.mobilechallenget.R
import com.gb.vale.mobilechallenget.components.CircleAvatar
import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.presentation.MainViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavController) {
    val context = LocalContext.current
    val handler = Handler(Looper.getMainLooper())
    val runnable = Runnable { viewModel.listFilter() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is RecipeUiEvent.NavigateToDetail -> {
                    navController.popBackStack()
                   // navController.navigate(Screen.)
                }

                is RecipeUiEvent.NavigateToHelps -> {
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBarContacts(
                context,
                viewModel
            )
        }
    ) {
        Box(modifier = Modifier.imePadding()) {
            ListInitialContact(context, viewModel)
            if (viewModel.uiState.searchQuery.isNotEmpty()) {
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 150)
                //ListSearchContact(context, viewModel)
            }
        }
    }

}

@Composable
fun TopAppBarContacts(
    context: Context,
    viewModel: MainViewModel
) {
    val successSearch = remember { mutableStateOf(false) }

    Box {
        TopAppBar(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth(),
            title = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column { Text( "Lista de recetas") }
                }
            }
        )
        SearchContact(successSearch, viewModel)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchContact(
    successSearch: MutableState<Boolean>,
    viewModel: MainViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = FocusRequester()
    if (successSearch.value) {
        TopAppBar(modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth(),
            backgroundColor = Color.White,
            navigationIcon = {
                IconButton(onClick = {
                    viewModel.onEvent(RecipeEvent.SearchContact(""))
                    successSearch.value = false
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            title = {
                BasicTextField(
                    modifier = Modifier
                        .wrapContentHeight()
                        .focusRequester(focusRequester),
                    value = viewModel.uiState.searchQuery,
                    cursorBrush = SolidColor(MaterialTheme.colors.primary),
                    onValueChange = {
                        viewModel.onEvent(RecipeEvent.SearchContact(it))
                    }
                )
            },
            actions = {
                IconButton(
                    modifier = Modifier.padding(start = 10.dp), onClick = {
                        viewModel.onEvent(RecipeEvent.SearchContact(""))
                        successSearch.value = true
                    }) {
                    Icon(
                        rememberVectorPainter(Icons.Sharp.Close),
                        contentDescription = null, tint = Color.Gray
                    )
                }

            }
        )
        LaunchedEffect(Unit) {
            if (successSearch.value) focusRequester.requestFocus()
            if (!successSearch.value) keyboardController?.hide()
        }
    }
}


@Composable
fun ListInitialContact(
    context: Context,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(45.dp)
            .verticalScroll(state = rememberScrollState())
    ) {

        if (viewModel.uiState.recipes.isNotEmpty()) {

            Text(
                text = "Recetas",
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                color = colorResource(id = R.color.gray_600)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                userScrollEnabled = false
            ) {
                items(viewModel.uiState.recipes) { recipe ->
                    ContactItem(
                        recipe = recipe,
                        openNewChatAction = { viewModel.onEvent(RecipeEvent.DetailContact) }
                    )
                }
            }
        }
    }
}

@Composable
fun ContactItem(
    recipe: RecipeModel,
    textSearch: String = "",
    openNewChatAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                openNewChatAction()
            }
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
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

        }
    }
}