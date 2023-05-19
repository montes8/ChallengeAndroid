package com.gb.vale.mobilechallenget

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.gb.vale.mobilechallenget.presentation.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    @Rule
    @JvmField
    var composeTestRule: ComposeContentTestRule = createAndroidComposeRule<MainActivity>()
    @Test
    fun whenIClickOnButton_TheTextShouldChange() {
        val button = composeTestRule.onNode(hasTestTag("buttonMapNext"), useUnmergedTree = true)
        button.assertIsDisplayed()
        button.performClick()
    }

}