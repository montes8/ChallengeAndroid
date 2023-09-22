package com.gb.vale.mobilechallenget.ui

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.gb.vale.mobilechallenget.R
import com.gb.vale.mobilechallenget.model.FlagButton.flagButton
import com.gb.vale.mobilechallenget.ui.base.BaseActivity
import com.gb.vale.mobilechallenget.ui.base.BaseViewModel
import com.gb.vale.mobilechallenget.ui.home.HomeViewModel
import com.gb.vale.mobilechallenget.ui.theme.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel: HomeViewModel by viewModels()
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    override fun SetScreenConfig() {
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

    override fun setDataGlobal() {
        viewModel.loadInsertDataBase()
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        if (flagButton)viewModel.floatingButton = true
        flagButton = true
    }

    override fun getViewModel(): BaseViewModel = viewModel
}
