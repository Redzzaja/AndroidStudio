import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul2_kel64.Network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.modul2_kel64.Model.AnimeListResponse
import com.example.modul2_kel64.Model.Anime
import com.example.modul2_kel64.Model.AnimeResponse


class AnimeViewModel : ViewModel() {
    private val _animeList =
        MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> = _animeList

    private val _animeDetail = MutableStateFlow<Anime?>(null)
    val animeDetail: StateFlow<Anime?> = _animeDetail

    // -- Blok Favorit Ditambahkan --
    private val _favoriteAnimes = MutableStateFlow<List<Anime>>(emptyList())
    val favoriteAnimes: StateFlow<List<Anime>> = _favoriteAnimes

    fun isFavorite(anime: Anime): Boolean {
        return _favoriteAnimes.value.any { it.mal_id == anime.mal_id }
    }

    fun addOrRemoveFavorite(anime: Anime) {
        val currentFavorites = _favoriteAnimes.value.toMutableList()
        if (isFavorite(anime)) {
            currentFavorites.removeAll { it.mal_id == anime.mal_id }
        } else {
            currentFavorites.add(anime)
        }
        _favoriteAnimes.value = currentFavorites
    }
    // -- Akhir Blok Favorit --

    fun fetchTopAnime() {
        viewModelScope.launch {
            try {
                val response: AnimeListResponse =
                    ApiClient.service.getTopAnime()
                _animeList.value = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchAnimeById(animeId: Int) {
        viewModelScope.launch {
            try {
                val response: AnimeResponse = ApiClient.service.getAnimeById(animeId)
                _animeDetail.value = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}