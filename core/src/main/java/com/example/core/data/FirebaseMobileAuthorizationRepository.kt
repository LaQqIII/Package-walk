package com.example.core.data

import com.example.core.domain.MyResult

class FirebaseMobileAuthorizationRepository(
    private val dataSource: MobileAuthorizationDataSource
) : MobileAuthorization {

    override suspend fun sendVerificationCode(phoneNumber: String): MyResult<Boolean> =
        dataSource.sendCode(phoneNumber)

    override suspend fun checkVerificationCode(code: String): Boolean = dataSource.checkCode(code)

    override suspend fun signInWithPhone(): MyResult<Boolean> = dataSource.signIn()
}