package com.example.packagewalk.ui.screens.authorization

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.MyResult
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
@Inject constructor(private val repository: PhoneAuthorizationRepository) : ViewModel() {

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

    val authorizationEvent = MutableLiveData(CREATE)

    val codeInCorrect = mutableStateOf(false)

    fun sendCode(phoneNumber: String, context: Context) {
        when (repository.sendCode(phoneNumber, context)) {
            is MyResult.Success -> authorizationEvent.postValue(CODE_SEND)
            is MyResult.Error -> authorizationEvent.postValue(CODE_NOT_SEND)
        }
    }

    fun checkCode(code: String) {
        when (repository.checkCode(code)) {
            true -> authorizationEvent.postValue(CODE_CORRECT)
            false -> codeInCorrect.value = true
        }
    }

    fun signIn() = viewModelScope.launch(Dispatchers.IO) {
        when (repository.signIn()) {
            is MyResult.Success -> authorizationEvent.postValue(USER_LOGIN)
            is MyResult.Error -> authorizationEvent.postValue(USER_FAILED_LOGIN)
        }
    }
}