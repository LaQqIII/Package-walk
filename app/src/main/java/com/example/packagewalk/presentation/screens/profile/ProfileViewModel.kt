package com.example.packagewalk.presentation.screens.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.core.domain.User
import com.example.packagewalk.framework.Interactors
import com.example.packagewalk.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(private val interactors: Interactors) : BaseViewModel() {

    val userLoggedIn = mutableStateOf(false)

    init {
        checkLoggedIn()
    }

    private fun checkLoggedIn() = viewModelScope.launch {
        userLoggedIn.value = User.loggedIn
    }
}