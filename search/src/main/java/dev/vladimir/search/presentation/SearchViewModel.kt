package dev.vladimir.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.search.domain.SearchInteractor
import dev.vladimir.search.domain.model.Media
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private val mutableSearchMoviesState =
        MutableStateFlow<PagingData<Media>>(PagingData.empty())
    val searchMoviesState: StateFlow<PagingData<Media>> = mutableSearchMoviesState

    init {
        getSearchMovies()
    }

    private fun getSearchMovies() {
        viewModelScope.launch {
            searchInteractor.getPagingSearchMovies("thei")
                .cachedIn(viewModelScope)
                .collect(mutableSearchMoviesState::emit)
        }
    }
}
