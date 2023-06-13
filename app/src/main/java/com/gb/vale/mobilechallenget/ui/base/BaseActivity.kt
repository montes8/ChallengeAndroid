package com.gb.vale.mobilechallenget.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gb.vale.mobilechallenget.components.AlertDialogGeneric
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
                if (getViewModel()?.uiLoading == true){ ProgressDialog()}
                if (getViewModel()?.uiError == true){
                    AlertDialogGeneric(
                        title  = "titulo del error",
                        text = "Probando el mensaje de errior",
                        dismissButton = "Cancelar",
                        confirmButton = "Aceptar",
                        showDialog = getViewModel()?.uiError == true,
                        onConfirmButton = {
                            getViewModel()?.uiError = false
                        }) {
                        getViewModel()?.uiError = false
                    }
                }
                SetScreenConfig()
            }
        }
        setDataGlobal()
    }

}
