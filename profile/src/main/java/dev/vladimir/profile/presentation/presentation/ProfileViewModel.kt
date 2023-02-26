package dev.vladimir.profile.presentation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.core.data.common.models.Result
import dev.vladimir.core.presentation.model.LoadState
import dev.vladimir.profile.presentation.domain.ProfileInteractor
import dev.vladimir.profile.presentation.domain.model.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
) : ViewModel() {

    private val mutableProfileState =
        MutableStateFlow<LoadState<Profile>>(LoadState.Loading())
    val profileState: StateFlow<LoadState<Profile>> = mutableProfileState

    fun getProfile() {
        viewModelScope.launch {
            profileInteractor.getProfile()
                .collect {
                    when (it) {
                        is Result.Success -> mutableProfileState.emit(LoadState.Success(it.data))
                        is Result.Error -> mutableProfileState.emit(LoadState.Error(it.error))
                    }
                }
        }
    }
}