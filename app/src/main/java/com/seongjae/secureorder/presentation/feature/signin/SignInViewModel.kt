package com.seongjae.secureorder.presentation.feature.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seongjae.secureorder.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state

    fun signIn() = viewModelScope.launch {
        val email = _state.value.email
        val password = _state.value.password

        runCatching {
            authRepository.signIn(email, password)
        }.onSuccess { auth ->
            _state.value =
                SignInState(viewState = SignInViewState.Authenticated(auth.accessToken))
        }.onFailure { error ->
            _state.value =
                SignInState(viewState = SignInViewState.Error(error.message))
        }
    }

    fun onEmailChanged(email: String) =
        _state.update { _state.value.copy(email = email) }

    fun onPasswordChanged(password: String) =
        _state.update { _state.value.copy(password = password) }
}

