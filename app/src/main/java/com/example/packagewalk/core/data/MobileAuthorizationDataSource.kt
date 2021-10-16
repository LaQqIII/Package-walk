package com.example.packagewalk.core.data

import android.content.Context
import com.example.packagewalk.core.domain.MyResult

interface MobileAuthorizationDataSource {

    suspend fun sendCode(phoneNumber: String, context: Context): MyResult<Boolean>

    suspend fun checkCode(code: String): Boolean

    suspend fun signIn(): MyResult<Boolean>
}