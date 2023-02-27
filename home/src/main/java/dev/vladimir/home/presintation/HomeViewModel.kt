package dev.vladimir.home.presintation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.home.domain.MovieInteractor
import dev.vladimir.home.domain.model.Media
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor,
) : ViewModel() {

    private val mutablePopularMoviesState =
        MutableStateFlow<PagingData<Media>>(PagingData.empty())
    val popularMoviesState: StateFlow<PagingData<Media>> = mutablePopularMoviesState

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            movieInteractor.getPagingPopularMovies()
                .cachedIn(viewModelScope)
                .collect(mutablePopularMoviesState::emit)
        }
    }
}