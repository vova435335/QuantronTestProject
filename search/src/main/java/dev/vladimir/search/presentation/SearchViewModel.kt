package dev.vladimir.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.search.domain.SearchInteractor
import dev.vladimir.search.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private val mutableSearchMoviesState =
        MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val searchMoviesState: StateFlow<PagingData<Movie>> = mutableSearchMoviesState

    init {
        getSearchMovies()
    }

    private fun getSearchMovies() {
        viewModelScope.launch {
            searchInteractor.getPagingSearchMovies("k")
                .cachedIn(viewModelScope)
                .collect(mutableSearchMoviesState::emit)
        }
    }
}
