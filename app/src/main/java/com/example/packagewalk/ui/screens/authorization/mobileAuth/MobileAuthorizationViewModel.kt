package com.example.packagewalk.ui.screens.authorization.mobileAuth

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MobileAuthorizationViewModel
@Inject constructor(private val sendVerificationCode: SendVerificationCode) : ViewModel() {

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

    fun sendCode() = viewModelScope.launch {
        sendVerificationCode(_phoneNumber.value)
    }
}

interface SendVerificationCode {
    suspend operator fun invoke(phoneNumber: String)
}

class SendVerificationCodeFirebase
@Inject constructor(@ActivityContext private val context: Context) : SendVerificationCode {

    override suspend fun invoke(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(context as Activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    TODO("Not yet implemented")
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    TODO("Not yet implemented")
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}

@Module
@InstallIn(ActivityComponent::class)
class Test {
    @Provides
    fun providesSendVerificationCode(impl: SendVerificationCodeFirebase): SendVerificationCode =
        impl
}