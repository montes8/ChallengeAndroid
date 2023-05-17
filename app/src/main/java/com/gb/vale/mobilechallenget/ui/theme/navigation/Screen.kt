package com.gb.vale.mobilechallenget.ui.theme.navigation

const val ROOT_GRAPH_ROUTE = "root"
const val HOME_GRAPH_ROUTE = "authentication"

sealed class Screen (open val route: String) {
    object HomeScreen : Screen("home_screen")
    object DetailScreen : Screen("detail_screen")
    object MapScreen : Screen("map_screen")

    fun withArgs(vararg args: String = emptyArray(), optional: Map<String, String?> = emptyMap()): String {
    return buildString {
            append(route)
            args.forEach { append("/$it") }

            if(optional.isNotEmpty()) {
                append("?")
            }

            optional.entries.forEachIndexed { index, map ->
                map.value?.let {
                    if(index != 0) append("&")

                    append("${map.key}=${map.value}")
                }
            }
        }
    }
}


