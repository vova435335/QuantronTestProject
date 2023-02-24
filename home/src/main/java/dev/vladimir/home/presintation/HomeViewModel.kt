package dev.vladimir.home.presintation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.home.data.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            Log.d("qqq", "${homeRepository.getPopularMovies()}")
        }
    }
}