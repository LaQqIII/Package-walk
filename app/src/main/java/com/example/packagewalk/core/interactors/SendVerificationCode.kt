package com.example.packagewalk.core.interactors

import android.content.Context
import com.example.packagewalk.core.data.MobileAuthorization
import com.example.packagewalk.core.domain.MyResult
import javax.inject.Inject

class SendVerificationCode
@Inject constructor(private val mobileRepository: MobileAuthorization) {
    /**
     * Send verification code on the specified phoneNumber
     * @param phoneNumber - where send the code
     */
    suspend operator fun invoke(phoneNumber: String, context: Context): MyResult<Boolean> =
        mobileRepository.sendVerificationCode(phoneNumber, context)
}