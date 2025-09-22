package com.example.modul2_kel64.Network;


import com.example.modul2_kel64.Model.AnimeListResponse;
import com.example.modul2_kel64.Model.AnimeResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface ApiService {
    // Anime by id
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeListResponse

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") animeId: Int): AnimeResponse
}