package com.example.packagewalk.ui.screens.authorization

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.CurrentCargoruan
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.references.Сargoruan
import com.example.packagewalk.repositories.CargoruanRepository
import com.example.packagewalk.repositories.FirebaseUserLiveData
import com.example.packagewalk.repositories.PhoneAuthorizationRepository
import com.example.packagewalk.ui.screens.authorization.models.AuthenticationState
import com.example.packagewalk.ui.screens.authorization.models.AuthorizationEventState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel
@Inject constructor(
    private val authRepository: PhoneAuthorizationRepository,
    private val cargoruanRepository: CargoruanRepository
) : ViewModel() {

    val name = mutableStateOf("")

    val phoneNumber = mutableStateOf("")

    val authenticationState = FirebaseUserLiveData().map { _user ->
        if (_user != null) {
            if (!CurrentCargoruan.identified) identifyCurrentCargoruan(_user.phoneNumber ?: "")
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

    val authorizationEvent = mutableStateOf(CREATE)

    private val codeInCorrect = mutableStateOf(false)

    fun sendCode(context: Context) {
        when (authRepository.sendCode(phoneNumber.value, context)) {
            is MyResult.Success -> authorizationEvent.value = CODE_SEND
            is MyResult.Error -> authorizationEvent.value = CODE_NOT_SEND
        }
    }

    fun checkCode(code: String) {
        when (authRepository.checkCode(code)) {
            true -> authorizationEvent.value = CODE_CORRECT
            false -> codeInCorrect.value = true
        }
    }

    fun signIn() = viewModelScope.launch(Dispatchers.IO) {
        when (authRepository.signIn()) {
            is MyResult.Success -> addNewUser()
            is MyResult.Error -> authorizationEvent.value = USER_FAILED_LOGIN
        }
    }

    private suspend fun addNewUser() {
        when (val result = cargoruanRepository.addSupporters(
            Сargoruan(
                phoneNumber = phoneNumber.value,
                name = name.value
            )
        )) {
            is MyResult.Success -> {
                if (result.data) {
                    authorizationEvent.value = USER_LOGIN
                } else {
                    authorizationEvent.value = USER_FAILED_LOGIN
                }
            }
            is MyResult.Error -> {}
        }
    }

    private fun identifyCurrentCargoruan(phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = cargoruanRepository.issueCargoruan(phoneNumber)) {
                is MyResult.Success -> {
                    CurrentCargoruan.name = result.data?.name
                    CurrentCargoruan.phoneNumber = result.data?.phoneNumber
                    CurrentCargoruan.identified = true
                }
                is MyResult.Error -> {}
            }
        }
    }
}