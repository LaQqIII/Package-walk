package com.example.packagewalk.framework.mobileAuthorization

import android.app.Activity
import android.content.Context
import com.example.packagewalk.core.data.MobileAuthorizationDataSource
import com.example.packagewalk.core.domain.MyResult
import com.example.packagewalk.core.domain.User
import com.example.packagewalk.core.domain.err
import com.example.packagewalk.core.domain.success
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseMobileAuthorization
@Inject constructor() : MobileAuthorizationDataSource {

    private lateinit var _verificationId: String

    private lateinit var _token: ForceResendingToken

    private lateinit var _smsCode: String

    private lateinit var _credential: PhoneAuthCredential

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        /**
         * This callback will be invoked in two situations:
         * 1 - Instant verification. In some cases the phone number can be instantly
         *  verified without needing to send or enter a verification code.
         * 2 - Auto-retrieval. On some devices Google Play services can automatically
         *  detect the incoming verification SMS and perform verification without
         *  user action
         */
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            Timber.d("!@# verification completed $phoneAuthCredential")
            _smsCode = phoneAuthCredential.smsCode.orEmpty()
            _credential = phoneAuthCredential
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
            Timber.d("!@# token=$token")

            _verificationId = verificationId
            _token = token
        }
    }

    override suspend fun sendCode(phoneNumber: String, context: Context): MyResult<Boolean> {
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

    override suspend fun checkCode(code: String): Boolean {
        Timber.d("!@# check verification code reference=$_smsCode user=$code")

        return _smsCode == code
    }

    override suspend fun signIn(): MyResult<Boolean> {
        Timber.d("!@# signIn with phone")

        return try {

            Firebase.auth
                .signInWithCredential(_credential)
                .await()

            Timber.d("!@# signIn success!")

            // Пока решил так определять, что пользователь вошел в приложение
            User.loggedIn = true

            success(true)

        } catch (e: Exception) {
            Timber.d("!@# error on signIn with phone ${e.cause}")
            err(e)
        }
    }
}