package com.example.packagewalk.data.usecases.impl

import android.content.Context
import com.example.packagewalk.data.authorization.mobile.RepositoryMobileAuthorization
import com.example.packagewalk.data.usecases.SendVerificationCode
import javax.inject.Inject

class FirebaseSendVerificationCode
@Inject constructor(private val repository: RepositoryMobileAuthorization) : SendVerificationCode {

    override suspend fun invoke(phoneNumber: String, context: Context) {
        repository.sendVerificationCode(phoneNumber, context)
    }
}