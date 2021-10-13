package com.example.packagewalk.ui.screens.authorization.mobileAuth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.usecases.CheckVerificationCode
import com.example.packagewalk.data.usecases.SendVerificationCode
import com.example.packagewalk.data.usecases.SignInWithPhone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterCodeViewModel
@Inject constructor(
    private val signInWithPhone: SignInWithPhone,
    private val checkVerificationCode: CheckVerificationCode,
    private val sendVerificationCode: SendVerificationCode
) : ViewModel() {

    private val _code = mutableStateOf("")
    val code: State<String> = _code

    private val _codeIsValid = mutableStateOf(false)
    val codeIsValid: State<Boolean> = _codeIsValid

    fun setCode(value: String) {
        if (value.length <= CODE_LENGTH) {
            _code.value = value
        }
        _codeIsValid.value = value.length == CODE_LENGTH
    }

    fun checkCode() = viewModelScope.launch {
        checkVerificationCode(_code.value)
    }

    companion object {
        private const val CODE_LENGTH = 6
    }
}