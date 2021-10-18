package com.example.packagewalk.framework

import com.example.packagewalk.core.interactors.*
import javax.inject.Inject

data class Interactors @Inject constructor(
    val checkVerificationCode: CheckVerificationCode,
    val sendVerificationCode: SendVerificationCode,
    val signInWithPhone: SignInWithPhone,
    val checkLoggedInUser: CheckLoggedInUser,
    val changeLoggedInUser: ChangeLoggedInUser
)