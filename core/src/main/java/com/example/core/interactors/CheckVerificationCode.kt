package com.example.core.interactors

import com.example.core.data.MobileAuthorization

//class CheckVerificationCode(private val mobileRepository: MobileAuthorization) {
class CheckVerificationCode() {
    /**
     * Should be return true - when enter user code equals verification code send on the phone
     * return false - when user code not equals
     */
    suspend operator fun invoke(code: String): Boolean = false
}