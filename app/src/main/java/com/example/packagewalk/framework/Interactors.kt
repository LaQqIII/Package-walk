package com.example.packagewalk.framework

import com.example.packagewalk.core.interactors.CheckVerificationCode
import com.example.packagewalk.core.interactors.SendVerificationCode
import com.example.packagewalk.core.interactors.SignInWithPhone
import javax.inject.Inject

data class Interactors @Inject constructor(
    val checkVerificationCode: CheckVerificationCode,
    val sendVerificationCode: SendVerificationCode,
    val signInWithPhone: SignInWithPhone
)