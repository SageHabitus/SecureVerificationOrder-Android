package com.seongjae.secureorder.presentation.main

sealed class AuthState {
    data object Unauthenticated : AuthState()
    data object Authenticated : AuthState()
}