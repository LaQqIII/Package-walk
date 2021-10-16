package com.example.packagewalk.core.data

import android.content.Context
import com.example.packagewalk.core.domain.MyResult

interface MobileAuthorization {

    suspend fun sendVerificationCode(phoneNumber: String, context: Context): MyResult<Boolean>

    suspend fun checkVerificationCode(code: String): Boolean

    suspend fun signInWithPhone(): MyResult<Boolean>
}