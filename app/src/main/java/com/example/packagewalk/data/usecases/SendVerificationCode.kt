package com.example.packagewalk.data.usecases

import android.content.Context

interface SendVerificationCode {
    suspend operator fun invoke(phoneNumber: String, context: Context)
}