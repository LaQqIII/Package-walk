package com.example.core.interactors

import com.example.core.data.MobileAuthorization
import com.example.core.domain.MyResult

class SendVerificationCode(private val mobileRepository: MobileAuthorization) {

    /**
     * Send verification code on the specified phoneNumber
     * @param phoneNumber - where send the code
     */
    suspend operator fun invoke(phoneNumber: String): MyResult<Boolean> =
        mobileRepository.sendVerificationCode(phoneNumber)
}