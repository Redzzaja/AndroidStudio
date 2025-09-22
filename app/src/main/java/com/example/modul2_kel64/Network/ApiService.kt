package com.example.modul2_kel64.Network;


import com.example.modul2_kel64.Model.AnimeListResponse;

import retrofit2.http.GET;
interface ApiService {
// Anime by id
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeListResponse
}
