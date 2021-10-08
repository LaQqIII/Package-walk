package com.example.packagewalk.ui.screens.authorization.mobileAuth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MobileAuthorizationViewModel @Inject constructor() : ViewModel() {

    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber

    private val _phoneNumberIsValid = mutableStateOf(false)
    val phoneNumberIsValid: State<Boolean> = _phoneNumberIsValid

    fun setPhoneNumber(value: String) {
        if (value.length <= 10) {
            _phoneNumber.value = value
            _phoneNumberIsValid.value = value.length == 10
        }
    }
}