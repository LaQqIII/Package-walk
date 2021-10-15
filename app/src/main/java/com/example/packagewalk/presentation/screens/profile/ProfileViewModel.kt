package com.example.packagewalk.presentation.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.framework.Interactors
import com.example.packagewalk.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(private val interactors: Interactors) : BaseViewModel() {

    private val _userLoggedIn = mutableStateOf(false)
    val userLoggedIn: State<Boolean> = _userLoggedIn

    init {
        checkLoggedIn()
    }

    private fun checkLoggedIn() = viewModelScope.launch {
//        when (val result = checkLoggedInUser()) {
//            is Result.Success -> _userLoggedIn.value = result.data
//            is Result.Error -> failure.value = ""
//        }
        _userLoggedIn.value = false
    }
}