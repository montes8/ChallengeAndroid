package com.gb.vale.mobilechallenget.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gb.vale.mobilechallenget.presentation.home.HomeViewModel
import com.gb.vale.mobilechallenget.ui.theme.MobileChallengeTTheme
import com.gb.vale.mobilechallenget.ui.theme.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileChallengeTTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation()
                }
            }
        }

        viewModel.loadInsertDataBase()
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MobileChallengeTTheme {
        Greeting("Android")
    }
}