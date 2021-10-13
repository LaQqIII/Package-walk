package com.example.packagewalk.data.usecases.impl

import com.example.packagewalk.data.authorization.mobile.RepositoryMobileAuthorization
import com.example.packagewalk.data.usecases.SignInWithPhone
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject

class FirebaseSignInWithPhone
@Inject constructor(private val repository: RepositoryMobileAuthorization) : SignInWithPhone {

    override suspend fun invoke(credential: PhoneAuthCredential) {
        repository.signInWithPhone()
    }
}