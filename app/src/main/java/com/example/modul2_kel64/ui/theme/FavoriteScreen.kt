package com.example.modul2_kel64.ui.theme

import AnimeViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun FavoriteScreen(viewModel: AnimeViewModel = viewModel(), navController: NavController) {
    val favoriteAnimes by viewModel.favoriteAnimes.collectAsState()

    if (favoriteAnimes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Belum ada anime favorit.", textAlign = TextAlign.Center)
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(favoriteAnimes) { anime ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(Screen.AnimeDetail.createRoute(anime.mal_id))
                        }
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
                            contentDescription = anime.title,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(anime.title)
                            Text("Type: ${anime.type ?: "-"}")
                            Text("Episodes: ${anime.episodes ?: 0}")
                            Text("Score: ${anime.score ?: "N/A"}")
                        }
                    }
                }
            }
        }
    }
}