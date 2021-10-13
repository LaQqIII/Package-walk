package com.example.packagewalk.data.firebase

import com.google.firebase.auth.PhoneAuthCredential

interface SignInWithPhone {
    suspend operator fun invoke(credential: PhoneAuthCredential)
}