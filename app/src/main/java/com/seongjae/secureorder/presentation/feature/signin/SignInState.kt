package com.seongjae.secureorder.presentation.feature.signin

data class SignInState(
    val email: String = "",
    val password: String = "",
    val viewState: SignInViewState = SignInViewState.Idle,
)

sealed interface SignInViewState {
    data object Idle : SignInViewState
    data object Loading : SignInViewState
    data class Authenticated(val token: String) : SignInViewState
    data class Error(val message: String?) : SignInViewState
}