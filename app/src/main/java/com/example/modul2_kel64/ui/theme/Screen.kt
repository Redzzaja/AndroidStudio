package com.example.modul2_kel64.ui.theme

sealed class Screen(val route: String, val title: String) {
    object Anime : Screen("anime", "Anime")
    object About : Screen("about", "About")
    object Favorite : Screen("favorite", "Favorite")
    object AnimeDetail : Screen("anime/{animeId}", "Anime Detail") {
        fun createRoute(animeId: Int) = "anime/$animeId"
    }
}