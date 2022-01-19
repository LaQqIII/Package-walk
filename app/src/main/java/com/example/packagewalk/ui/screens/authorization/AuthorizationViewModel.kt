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
import com.google.firebase.auth.FirebaseUser
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

    private var user: FirebaseUser? = null

    val name = mutableStateOf("")

    val authenticationState = FirebaseUserLiveData().map { _user ->
        user = _user
        if (_user != null) {
            if (!CurrentCargoruan.identified) identifyCurrentCargoruan()
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

    val authorizationEvent = mutableStateOf(CREATE)

    val codeInCorrect = mutableStateOf(false)

    fun sendCode(phoneNumber: String, context: Context) {
        when (authRepository.sendCode(phoneNumber, context)) {
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
            is MyResult.Success -> authorizationEvent.value = ADD_SUPPORTERS
            is MyResult.Error -> authorizationEvent.value = USER_FAILED_LOGIN
        }
    }

    fun addNewUser() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = cargoruanRepository.addSupporters(
            Сargoruan(
                uid = user!!.uid,
                phoneNumber = user!!.phoneNumber ?: "",
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

    private fun identifyCurrentCargoruan() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = cargoruanRepository.issueCargoruan(user!!.uid)) {
            is MyResult.Success -> {
                result.data?.apply {
                    CurrentCargoruan.uid = uid
                    CurrentCargoruan.name = name
                    CurrentCargoruan.phoneNumber = phoneNumber
                    CurrentCargoruan.markedForDeletion = markedForDeletion
                    CurrentCargoruan.identified = true
                }
            }
            is MyResult.Error -> {}
        }
    }
}