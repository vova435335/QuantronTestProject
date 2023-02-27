package dev.vladimir.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.core.data.common.models.Result
import dev.vladimir.core.presentation.model.LoadState
import dev.vladimir.details.domain.MediaDetailsInteractor
import dev.vladimir.details.domain.model.MediaDetailsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaDetailsViewModel @Inject constructor(
    private val mediaDetailsInteractor: MediaDetailsInteractor,
) : ViewModel() {

    private val mutableMediaDetailsState =
        MutableStateFlow<LoadState<MediaDetailsModel>>(LoadState.Loading())
    val mediaDetailsState: StateFlow<LoadState<MediaDetailsModel>> = mutableMediaDetailsState

    fun getMediaDetails(mediaId: String) {
        viewModelScope.launch {
            when (val result = mediaDetailsInteractor.getMovieDetails(mediaId)) {
                is Result.Success ->  mutableMediaDetailsState.emit(LoadState.Success(result.data))
                is Result.Error -> mutableMediaDetailsState.emit(LoadState.Error(result.error))
            }
        }
    }
}
