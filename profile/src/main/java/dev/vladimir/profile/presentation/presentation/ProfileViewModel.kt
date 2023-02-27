package dev.vladimir.profile.presentation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.core.data.common.models.Result
import dev.vladimir.core.presentation.model.LoadState
import dev.vladimir.profile.presentation.domain.ProfileInteractor
import dev.vladimir.profile.presentation.domain.model.Profile
import dev.vladimir.session.data.storage.SessionStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val sessionStorage: SessionStorage,
) : ViewModel() {

    private val mutableProfileState =
        MutableStateFlow<LoadState<Profile>>(LoadState.Loading())
    val profileState: StateFlow<LoadState<Profile>> = mutableProfileState

    private val mutableAuthState = MutableStateFlow(false)
    val authState: StateFlow<Boolean> = mutableAuthState

    init {
        checkAuth()
        getProfile()
    }

    fun getProfile() {
        viewModelScope.launch {
            profileInteractor.getProfile().collect {
                when (it) {
                    is Result.Success -> mutableProfileState.emit(LoadState.Success(it.data))
                    is Result.Error -> mutableProfileState.emit(LoadState.Error(it.error))
                }
            }
        }
    }

    private fun checkAuth() {
        viewModelScope.launch {
            if (sessionStorage.getSessionId() == null) {
                mutableAuthState.emit(false)
            } else {
                mutableAuthState.emit(true)
            }
        }
    }
}