package com.example.packagewalk.ui.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.Result
import com.example.packagewalk.data.usecases.CheckLoggedInUser
import com.example.packagewalk.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(private val checkLoggedInUser: CheckLoggedInUser) : BaseViewModel() {

    private val _userLoggedIn = mutableStateOf(false)
    val userLoggedIn: State<Boolean> = _userLoggedIn

    init {
        checkLoggedIn()
    }

    private fun checkLoggedIn() = viewModelScope.launch {
        when (val result = checkLoggedInUser()) {
            is Result.Success -> _userLoggedIn.value = result.data
            is Result.Error -> failure.value = ""
        }
    }
}