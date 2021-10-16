package com.example.packagewalk.core.data

import android.content.Context
import com.example.packagewalk.core.domain.MyResult
import javax.inject.Inject

class FirebaseMobileAuthorizationRepository
@Inject constructor(private val dataSource: MobileAuthorizationDataSource) : MobileAuthorization {

    override suspend fun sendVerificationCode(
        phoneNumber: String,
        context: Context
    ): MyResult<Boolean> =
        dataSource.sendCode(phoneNumber, context)

    override suspend fun checkVerificationCode(code: String): Boolean = dataSource.checkCode(code)

    override suspend fun signInWithPhone(): MyResult<Boolean> = dataSource.signIn()
}