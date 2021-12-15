package com.example.packagewalk.ui.screens.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.packagewalk.repositories.FirebaseUserLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() : ViewModel() {
    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}

enum class AuthenticationState {
    AUTHENTICATED, UNAUTHENTICATED
}