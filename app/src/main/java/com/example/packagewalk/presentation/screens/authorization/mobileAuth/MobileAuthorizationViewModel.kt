package com.example.packagewalk.presentation.screens.authorization.mobileAuth

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.packagewalk.framework.Interactors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MobileAuthorizationViewModel
@Inject constructor() : ViewModel() {

    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber

    private val _phoneNumberIsValid = mutableStateOf(false)
    val phoneNumberIsValid: State<Boolean> = _phoneNumberIsValid

    fun setPhoneNumber(value: String) {
        if (value.length <= PHONE_NUMBER_LENGTH) {
            _phoneNumber.value = value
        }
        _phoneNumberIsValid.value = value.length == PHONE_NUMBER_LENGTH
    }

    @DelicateCoroutinesApi
    fun sendCode(context: Context) = GlobalScope.launch {
       // interactors.sendVerificationCode("+7${_phoneNumber.value}")
    }

    companion object {
        private const val PHONE_NUMBER_LENGTH = 10
    }
}