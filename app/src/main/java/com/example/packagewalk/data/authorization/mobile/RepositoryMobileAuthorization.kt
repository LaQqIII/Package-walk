package com.example.packagewalk.data.authorization.mobile

import android.content.Context
import com.example.packagewalk.data.Result

interface RepositoryMobileAuthorization {

    suspend fun sendVerificationCode(phoneNumber: String, context: Context): Result<Boolean>

    suspend fun checkVerificationCode(code: String): Boolean

    suspend fun signInWithPhone(): Result<Boolean>
}