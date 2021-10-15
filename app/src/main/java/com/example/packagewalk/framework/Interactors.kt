package com.example.packagewalk.framework

import com.example.core.interactors.CheckVerificationCode
import javax.inject.Inject

data class Interactors @Inject constructor(
    val id: Int
    //val checkVerificationCode: CheckVerificationCode,
//    val sendVerificationCode: SendVerificationCode,
//    val signInWithPhone: SignInWithPhone
)