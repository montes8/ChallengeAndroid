package com.gb.vale.mobilechallenget.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.gb.vale.mobilechallenget.presentation.map.MapScreen
import com.gb.vale.mobilechallenget.presentation.map.MapViewModel
import com.gb.vale.mobilechallenget.ui.theme.MobileChallengeTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapUpdateActivity : ComponentActivity() {

    private val viewModel: MapViewModel by viewModels()

    companion object{
        fun newInstance(context: Context)=context.startActivity(
            Intent(context,MapUpdateActivity::class.java))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileChallengeTTheme {
               MapScreen(viewModel)
            }
        }
    }
}