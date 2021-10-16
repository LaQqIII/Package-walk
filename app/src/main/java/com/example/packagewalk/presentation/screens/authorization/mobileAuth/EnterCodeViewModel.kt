package com.example.packagewalk.presentation.screens.authorization.mobileAuth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.core.domain.MyResult
import com.example.packagewalk.framework.Interactors
import com.example.packagewalk.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterCodeViewModel
@Inject constructor(private val interactors: Interactors) : BaseViewModel() {

    val code = mutableStateOf("")
    val codeIsValid = mutableStateOf(false)
    val codeIsError = mutableStateOf(false)
    val userLoggedIn = mutableStateOf(false)

    fun setCode(value: String) {
        if (value.length <= CODE_LENGTH) {
            code.value = value
        }
        codeIsValid.value = value.length == CODE_LENGTH
        clearCodeCheck()
    }

    fun signInWithCheckCode() = viewModelScope.launch {
        val codeIsCorrect = interactors.checkVerificationCode(code.value)

        if (codeIsCorrect) {
            when (val result = interactors.signInWithPhone()) {
                is MyResult.Success ->
                    if (result.data) {
                        userLoggedIn.value = true
                    } else {
                        // TODO: 16.10.2021
                    }
                is MyResult.Error -> {
                    // TODO: 16.10.2021
                }
            }
        } else {
            codeIsError.value = true
        }
    }

    private fun clearCodeCheck() {
        codeIsError.value = false
    }

    companion object {
        private const val CODE_LENGTH = 6
    }
}