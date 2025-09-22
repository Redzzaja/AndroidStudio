package com.example.modul2_kel64.ui.theme

import AnimeViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun AnimeDetailScreen(animeId: Int, viewModel: AnimeViewModel = viewModel()) {
    val animeDetail by viewModel.animeDetail.collectAsState()
    val isFavorite by viewModel.favoriteAnimes.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchAnimeById(animeId)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (animeDetail == null) {
            CircularProgressIndicator()
        } else {
            val anime = animeDetail!!
            val isCurrentlyFavorite = viewModel.isFavorite(anime)

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Box {
                        Image(
                            painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
                            contentDescription = anime.title,
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth()
                        )
                        IconButton(
                            onClick = { viewModel.addOrRemoveFavorite(anime) },
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector = if (isCurrentlyFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = if (isCurrentlyFavorite) Color.Red else Color.Gray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = anime.title, style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)
                    Text("Type: ${anime.type ?: "-"}")
                    Text("Episodes: ${anime.episodes ?: 0}")
                    Text("Score: ${anime.score ?: "N/A"}")
                }
            }
        }
    }
}