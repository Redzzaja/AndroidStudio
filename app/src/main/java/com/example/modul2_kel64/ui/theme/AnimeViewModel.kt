import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul2_kel64.Network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.modul2_kel64.Model.AnimeListResponse
import com.example.modul2_kel64.Model.Anime


class AnimeViewModel : ViewModel() {
    private val _animeList =
        MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> = _animeList
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
}