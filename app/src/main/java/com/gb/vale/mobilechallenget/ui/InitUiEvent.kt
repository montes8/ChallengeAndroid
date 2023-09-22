package com.gb.vale.mobilechallenget.ui

sealed class InitUiEvent {
    class NavigateToDetail(val data : String) : InitUiEvent()

    object NavigateToMap  : InitUiEvent()

    object NavigateToBack : InitUiEvent()
}