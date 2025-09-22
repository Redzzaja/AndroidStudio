package com.example.modul2_kel64.ui.theme

import AnimeViewModel // Pastikan ini diimpor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel // Pastikan ini diimpor
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.modul2_kel64.ui.theme.Modul2_Kel64Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul2_Kel64Theme {
                AnimeApp()
            }
        }
    }
}

@Composable
fun AnimeApp() {
    val navController = rememberNavController()
    val items = listOf(Screen.Anime, Screen.Favorite, Screen.About)

    // 1. Buat instance ViewModel di sini, di level tertinggi.
    val animeViewModel: AnimeViewModel = viewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStack?.destination?.route

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            when (screen) {
                                Screen.Anime ->
                                    Icon(Icons.Default.Movie, contentDescription = "Anime")
                                Screen.Favorite ->
                                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                                Screen.About ->
                                    Icon(Icons.Default.Info, contentDescription = "About")
                                is Screen.AnimeDetail -> {
                                    // Tidak perlu ikon
                                }
                            }
                        },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Anime.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Anime.route) {
                // 2. Teruskan instance ViewModel yang sama ke setiap layar.
                AnimeListScreen(viewModel = animeViewModel, navController = navController)
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(viewModel = animeViewModel, navController = navController)
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                Screen.AnimeDetail.route,
                arguments = listOf(navArgument("animeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val animeId = backStackEntry.arguments?.getInt("animeId") ?: 0
                AnimeDetailScreen(animeId = animeId, viewModel = animeViewModel)
            }
        }
    }
}