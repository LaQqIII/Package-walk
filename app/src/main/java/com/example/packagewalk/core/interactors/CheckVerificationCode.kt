package com.example.packagewalk.core.interactors

import com.example.packagewalk.core.data.MobileAuthorization
import javax.inject.Inject

class CheckVerificationCode
@Inject constructor(private val mobileRepository: MobileAuthorization) {
    /**
     * Should be return true - when enter user code equals verification code send on the phone
     * return false - when user code not equals
     */
    suspend operator fun invoke(code: String): Boolean =
        mobileRepository.checkVerificationCode(code)
}