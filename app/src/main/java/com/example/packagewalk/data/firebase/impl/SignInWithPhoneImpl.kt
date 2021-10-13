package com.example.packagewalk.data.firebase.impl

import com.example.packagewalk.data.firebase.SignInWithPhone
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class SignInWithPhoneImpl : SignInWithPhone {
    override suspend fun invoke(credential: PhoneAuthCredential) {
        Timber.d("!@# sign in with phone number")

        Firebase.auth
            .signInWithCredential(credential)
            .await()
            .user
    }
}