package com.example.packagewalk.presentation.screens.authorization.mobileAuth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.framework.Interactors
import com.example.packagewalk.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterCodeViewModel
@Inject constructor() : BaseViewModel() {

    private val _code = mutableStateOf("")
    val code: State<String> = _code

    private val _codeIsValid = mutableStateOf(false)
    val codeIsValid: State<Boolean> = _codeIsValid

    private val _codeIsError = mutableStateOf(false)
    val codeIsError: State<Boolean> = _codeIsError

    fun setCode(value: String) {
        if (value.length <= CODE_LENGTH) {
            _code.value = value
        }
        _codeIsValid.value = value.length == CODE_LENGTH
        clearCodeCheck()
    }

    fun signInWithCheckCode() = viewModelScope.launch {
        // val codeIsCorrect = interactors.checkVerificationCode(_code.value)

//        if (codeIsCorrect) {
//            val result = interactors.signInWithPhone()
//        } else {
//            _codeIsError.value = true
//        }
    }

    private fun clearCodeCheck() {
        _codeIsError.value = false
    }

    companion object {
        private const val CODE_LENGTH = 6
    }
}