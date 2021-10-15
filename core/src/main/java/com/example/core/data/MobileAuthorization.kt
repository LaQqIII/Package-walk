package com.example.core.data

import com.example.core.domain.MyResult

interface MobileAuthorization {

    suspend fun sendVerificationCode(phoneNumber: String): MyResult<Boolean>

    suspend fun checkVerificationCode(code: String): Boolean

    suspend fun signInWithPhone(): MyResult<Boolean>
}