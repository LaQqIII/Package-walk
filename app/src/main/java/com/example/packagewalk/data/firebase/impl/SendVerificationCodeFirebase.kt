package com.example.packagewalk.data.firebase.impl

import android.app.Activity
import android.content.Context
import com.example.packagewalk.data.firebase.SendVerificationCode
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SendVerificationCodeFirebase
@Inject constructor() : SendVerificationCode {

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        /**
         * This callback will be invoked in two situations:
         * 1 - Instant verification. In some cases the phone number can be instantly
         *  verified without needing to send or enter a verification code.
         * 2 - Auto-retrieval. On some devices Google Play services can automatically
         *  detect the incoming verification SMS and perform verification without
         *  user action
         */
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Timber.d("!@# verification completed $credential")
        }

        /**
         * This callback is invoked in an invalid request for verification is made,
         * for instance if the the phone number format is not valid.
         */
        override fun onVerificationFailed(e: FirebaseException) {
            Timber.e("!@# verification failed ${e.cause}")
        }

        /**
         * The SMS verification code has been sent to the provided phone number, we
         * now need to ask the user to enter the code and then construct a credential
         * by combining the code with a verification ID.
         */
        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Timber.d("!@# code send $verificationId")
        }
    }

    override suspend fun invoke(phoneNumber: String, context: Context) {
        Timber.d("!@# отправка кода верификации на номер +7$phoneNumber")

        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber("+7$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(context as Activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}