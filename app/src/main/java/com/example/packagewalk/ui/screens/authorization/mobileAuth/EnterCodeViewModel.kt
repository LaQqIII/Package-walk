package com.example.packagewalk.ui.screens.authorization.mobileAuth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnterCodeViewModel @Inject constructor() : ViewModel() {

    private val _code = mutableStateOf("")
    val code: State<String> = _code

    fun setCode(value: String) {
        _code.value = value
    }
}