package com.example.packagewalk.data.usecases

import android.content.Context

interface SendVerificationCode {
    /**
     * Send verification code on the specified phoneNumber
     * @param phoneNumber - where send the code
     * @param context - ActivityContext
     */
    suspend operator fun invoke(phoneNumber: String, context: Context)
}