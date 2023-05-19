package com.gb.vale.mobilechallenget.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.gb.vale.mobilechallenget.R
import com.gb.vale.mobilechallenget.model.FlagButton.flagButton
import com.gb.vale.mobilechallenget.presentation.home.HomeViewModel
import com.gb.vale.mobilechallenget.ui.theme.MobileChallengeTTheme
import com.gb.vale.mobilechallenget.ui.theme.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileChallengeTTheme {
                Scaffold(floatingActionButtonPosition = FabPosition.End
                ,floatingActionButton = {
                        if (viewModel.floatingButton){
                            FloatingActionButton(modifier = Modifier.testTag("buttonMapNext"), onClick = {

                                MapUpdateActivity.newInstance(this)
                            }) {
                                Icon(painter = painterResource(id = R.drawable.ic_map_custom), contentDescription = "next map")
                            }
                        }
                    }) {
                    Navigation(viewModel)
                }
            }
        }

        viewModel.loadInsertDataBase()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        if (flagButton)viewModel.floatingButton = true
        flagButton = true
    }
}
