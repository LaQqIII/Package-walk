package com.example.packagewalk.ui.screens.authorization.mobileAuth

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.firebase.SendVerificationCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MobileAuthorizationViewModel
@Inject constructor(private val sendVerificationCode: SendVerificationCode) : ViewModel() {

    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber

    private val _phoneNumberIsValid = mutableStateOf(false)
    val phoneNumberIsValid: State<Boolean> = _phoneNumberIsValid

    fun setPhoneNumber(value: String) {
        if (value.length <= PHONE_NUMBER_LENGTH) {
            _phoneNumber.value = value
            _phoneNumberIsValid.value = value.length == PHONE_NUMBER_LENGTH
        }
    }

    fun sendCode(context: Context) = viewModelScope.launch {
        sendVerificationCode(_phoneNumber.value, context)
    }

    companion object {
        private const val PHONE_NUMBER_LENGTH = 10
    }
}