package com.example.packagewalk.data.usecases

interface CheckVerificationCode {
    /**
     * Should be return true - when enter user code equals verification code send on the phone
     * return false - when user code not equals
     */
    suspend operator fun invoke(code: String): Boolean
}