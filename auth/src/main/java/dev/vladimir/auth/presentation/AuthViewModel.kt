package dev.vladimir.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.auth.R
import dev.vladimir.auth.domain.AuthInteractor
import dev.vladimir.core.data.common.models.Result
import dev.vladimir.core.presentation.model.LoadState
import dev.vladimir.core.utils.StringProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val stringProvider: StringProvider,
) : ViewModel() {

    private val mutableAuthState =
        MutableStateFlow<LoadState<Unit>>(LoadState.Init(Unit))
    val authState: StateFlow<LoadState<Unit>> = mutableAuthState

    fun login(login: String, password: String) {
        viewModelScope.launch {
            mutableAuthState.emit(LoadState.Loading())

            when (authInteractor.login(login, password)) {
                is Result.Success -> mutableAuthState.emit(LoadState.Success(Unit))
                is Result.Error -> mutableAuthState.emit(
                    LoadState.Error(stringProvider.getString(R.string.auth_error))
                )
            }
        }
    }
}