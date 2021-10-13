package com.example.packagewalk.ui.screens.authorization.mobileAuth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.packagewalk.data.firebase.SignInWithPhone
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnterCodeViewModel
@Inject constructor(private val signInWithPhone: SignInWithPhone) : ViewModel() {

    private val _code = mutableStateOf("")
    val code: State<String> = _code

    private val _codeIsValid = mutableStateOf(false)
    val codeIsValid: State<Boolean> = _codeIsValid

    fun setCode(value: String) {
        if (value.length <= CODE_LENGTH) {
            _code.value = value
            _codeIsValid.value = value.length == CODE_LENGTH
        }
    }

    fun enterToSystem() {

    }

    companion object {
        private const val CODE_LENGTH = 6
    }
}