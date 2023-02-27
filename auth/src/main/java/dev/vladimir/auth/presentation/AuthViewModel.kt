package dev.vladimir.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.auth.data.repository.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    init {
        login()
    }

    fun login() {
        viewModelScope.launch {
            profileRepository.login()
        }
    }
}