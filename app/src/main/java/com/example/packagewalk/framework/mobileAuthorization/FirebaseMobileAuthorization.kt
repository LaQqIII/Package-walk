package com.example.packagewalk.framework.mobileAuthorization

import com.example.core.data.MobileAuthorizationDataSource
import com.example.core.domain.MyResult
import com.example.core.domain.err
import com.example.core.domain.success
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

class FirebaseMobileAuthorization : MobileAuthorizationDataSource {

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
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Timber.d("!@# code send $verificationId")

            _verificationId = verificationId
            _token = token
        }
    }

    override suspend fun sendCode(phoneNumber: String): MyResult<Boolean> {
        Timber.d("!@# отправка кода верификации на номер $phoneNumber")

        return try {

//            val options = PhoneAuthOptions.newBuilder(Firebase.auth)
//                .setPhoneNumber(phoneNumber)
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setActivity(context as Activity)
//                .setCallbacks(callbacks)
//                .build()
//            PhoneAuthProvider.verifyPhoneNumber(options)

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

            success(true)

        } catch (e: Exception) {
            Timber.d("!@# error on signIn with phone ${e.cause}")
            err(e)
        }
    }
}