package com.example.core.data

import com.example.core.domain.MyResult

interface MobileAuthorizationDataSource {

    suspend fun sendCode(phoneNumber: String): MyResult<Boolean>

    suspend fun checkCode(code: String): Boolean

    suspend fun signIn(): MyResult<Boolean>
}