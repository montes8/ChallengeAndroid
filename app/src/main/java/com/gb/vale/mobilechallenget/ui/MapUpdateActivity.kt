package com.gb.vale.mobilechallenget.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.gb.vale.mobilechallenget.ui.map.MapScreen
import com.gb.vale.mobilechallenget.ui.map.MapViewModel
import com.gb.vale.mobilechallenget.utils.MobileChallengeTTheme
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