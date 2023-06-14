package com.gb.vale.mobilechallenget.ui.base


data class BaseUiState(
    var popUpGenericValue:Boolean = false,
    var popUpGeneric: Boolean = false,
    var loading: Boolean = false,
    var error: Boolean = false,
    var errorType: Throwable = Throwable()
)
