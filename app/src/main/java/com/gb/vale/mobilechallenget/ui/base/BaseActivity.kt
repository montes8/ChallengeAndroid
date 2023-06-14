package com.gb.vale.mobilechallenget.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.gb.vale.mobilechallenget.components.DialogGeneric
import com.gb.vale.mobilechallenget.components.ProgressDialog
import com.gb.vale.mobilechallenget.utils.MobileChallengeTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : ComponentActivity() {

    @Composable
    abstract fun SetScreenConfig()
    abstract fun setDataGlobal()
    open fun getViewModel(): BaseViewModel? = null

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileChallengeTTheme {
                if (getViewModel()?.uiStateBase?.loading == true){ ProgressDialog() }
                if (getViewModel()?.uiStateBase?.error == true){
                    DialogGeneric {
                        getViewModel()?.uiStateBase = getViewModel()?.uiStateBase?.copy(error = false,
                        popUpGeneric = true, popUpGenericValue = it)?:BaseUiState()
                    }
                }
                SetScreenConfig()
            }
        }
        setDataGlobal()
    }

}
