package com.example.packagewalk.data.usecases.impl

import com.example.packagewalk.data.Result
import com.example.packagewalk.data.authorization.mobile.RepositoryMobileAuthorization
import com.example.packagewalk.data.usecases.SignInWithPhone
import javax.inject.Inject

class FirebaseSignInWithPhone
@Inject constructor(private val repository: RepositoryMobileAuthorization) : SignInWithPhone {

    override suspend fun invoke(): Result<Boolean> = repository.signInWithPhone()
}