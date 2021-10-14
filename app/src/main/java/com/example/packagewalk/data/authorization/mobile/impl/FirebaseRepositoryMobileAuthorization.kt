package com.example.packagewalk.data.authorization.mobile.impl

import android.app.Activity
import android.content.Context
import com.example.packagewalk.data.Result
import com.example.packagewalk.data.authorization.mobile.RepositoryMobileAuthorization
import com.example.packagewalk.data.err
import com.example.packagewalk.data.success
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepositoryMobileAuthorization
@Inject constructor() : RepositoryMobileAuthorization {

    private lateinit var _verificationId: String

    private lateinit var _token: ForceResendingToken

    private lateinit var _smsCode: String

    private lateinit var _credential: PhoneAuthCredential

    private val callbacks = object : OnVerificationStateChangedCallbacks() {

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
            _smsCode = credential.smsCode.orEmpty()
            _credential = credential
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
            token: ForceResendingToken
        ) {
            Timber.d("!@# code send $verificationId")

            _verificationId = verificationId
            _token = token
        }
    }

    override suspend fun sendVerificationCode(
        phoneNumber: String,
        context: Context
    ): Result<Boolean> {
        Timber.d("!@# отправка кода верификации на номер $phoneNumber")

        return try {

            val options = PhoneAuthOptions.newBuilder(Firebase.auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(context as Activity)
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)

            success(true)

        } catch (e: Exception) {
            Timber.d("!@# emerged error at the time code verification")
            err(e)
        }
    }

    override suspend fun checkVerificationCode(code: String): Boolean {
        Timber.d("!@# check verification code reference=$_smsCode user=$code")

        return _smsCode == code
    }

    override suspend fun signInWithPhone(): Result<Boolean> {
        Timber.d("!@# signIn with phone")

        return try {

            Firebase.auth
                .signInWithCredential(_credential)
                .await()

            Timber.d("!@# signIn success!")

            success(true)

        } catch (e: Exception) {
            Timber.d("!@# error on signIn with phone ${e.cause}")
            err(e)
        }
    }
}