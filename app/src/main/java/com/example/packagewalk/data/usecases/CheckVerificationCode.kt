package com.example.packagewalk.data.usecases

interface CheckVerificationCode {
    suspend operator fun invoke(code: String): Boolean
}