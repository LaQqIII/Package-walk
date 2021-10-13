package com.example.packagewalk.data.usecases.impl

import com.example.packagewalk.data.authorization.mobile.RepositoryMobileAuthorization
import com.example.packagewalk.data.usecases.CheckVerificationCode
import javax.inject.Inject

class FirebaseCheckVerificationCode
@Inject constructor(private val repository: RepositoryMobileAuthorization) : CheckVerificationCode {

    override suspend fun invoke(code: String): Boolean = repository.checkVerificationCode(code)
}