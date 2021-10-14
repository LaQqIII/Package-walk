package com.example.packagewalk.ui.screens.authorization.mobileAuth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.usecases.CheckVerificationCode
import com.example.packagewalk.data.usecases.SendVerificationCode
import com.example.packagewalk.data.usecases.SignInWithPhone
import com.example.packagewalk.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterCodeViewModel
@Inject constructor(
    private val signInWithPhone: SignInWithPhone,
    private val checkVerificationCode: CheckVerificationCode,
    private val sendVerificationCode: SendVerificationCode
) : BaseViewModel() {

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
        val codeIsCorrect = checkVerificationCode(_code.value)

        if (codeIsCorrect) {
            val result = signInWithPhone()
        } else {
            _codeIsError.value = true
        }
    }

    private fun clearCodeCheck() {
        _codeIsError.value = false
    }

    companion object {
        private const val CODE_LENGTH = 6
    }
}