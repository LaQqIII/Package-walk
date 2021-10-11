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

    private val _codeIsValid = mutableStateOf(false)
    val codeIsValid: State<Boolean> = _codeIsValid

    init {

    }

    fun setCode(value: String) {
        if (value.length <= 4) {
            _code.value = value
            _codeIsValid.value = value.length == 4
        }
    }
}