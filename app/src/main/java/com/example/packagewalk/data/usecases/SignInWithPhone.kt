package com.example.packagewalk.data.usecases

import com.example.packagewalk.data.Result

interface SignInWithPhone {
    /**
     * Create new recording on db authentication*/
    suspend operator fun invoke(): Result<Boolean>
}