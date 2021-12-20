package com.example.packagewalk.repositories

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.packagewalk.data.MyResult
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhoneAuthorizationRepository @Inject constructor() {

    private lateinit var _verificationId: String

    private lateinit var _token: PhoneAuthProvider.ForceResendingToken

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
            Log.d("!@#", "verification completed $phoneAuthCredential")
            _smsCode = phoneAuthCredential.smsCode.orEmpty()
            _credential = phoneAuthCredential
        }

        /**
         * This callback is invoked in an invalid request for verification is made,
         * for instance if the the phone number format is not valid.
         */
        override fun onVerificationFailed(e: FirebaseException) {
            Log.e("!@#", "verification failed $e")
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
            Log.d("!@#", "code send $verificationId, token=$token")
            _verificationId = verificationId
            _token = token
        }
    }

    fun sendCode(phoneNumber: String, context: Context): MyResult<Boolean> {
        Log.d("!@#", "отправка кода верификации на номер $phoneNumber")
        return try {
            val options = PhoneAuthOptions.newBuilder(Firebase.auth)
                .setPhoneNumber("+7$phoneNumber")
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(context as Activity)
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            MyResult.Success(true)
        } catch (e: Exception) {
            Log.d("!@#", "ошибка при отправке кода верификации")
            MyResult.Error(e)
        }
    }

    fun checkCode(code: String): Boolean = _smsCode == code

    suspend fun signIn(): MyResult<Boolean> {
        Log.d("!@#", "попытка входа пользователя")
        return try {
            Firebase.auth
                .signInWithCredential(_credential)
                .await()
            Log.d("!@#", "пользователь залогинился!")
            MyResult.Success(true)
        } catch (e: Exception) {
            Log.d("!@#", "ошибка при входе пользователя=$e")
            MyResult.Error(e)
        }
    }
}