package com.example.packagewalk.data.usecases

import com.google.firebase.auth.PhoneAuthCredential

interface SignInWithPhone {
    suspend operator fun invoke(credential: PhoneAuthCredential)
}