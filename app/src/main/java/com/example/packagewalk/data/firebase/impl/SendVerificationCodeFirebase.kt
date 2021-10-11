package com.example.packagewalk.data.firebase.impl

import android.app.Activity
import android.content.Context
import com.example.packagewalk.data.firebase.SendVerificationCode
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SendVerificationCodeFirebase
@Inject constructor() : SendVerificationCode {

    override suspend fun invoke(phoneNumber: String, context: Context) {
        Timber.d("!@# отправка кода верификации на номер $+7$phoneNumber")

        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber("+7$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(context as Activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    TODO("Not yet implemented")
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Timber.d("!@# ошибка при отправке кода верификации")
                    Timber.d("${e.message}")
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}