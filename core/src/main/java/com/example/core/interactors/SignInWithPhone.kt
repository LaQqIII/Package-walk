package com.example.core.interactors

import com.example.core.data.MobileAuthorization
import com.example.core.domain.MyResult

class SignInWithPhone(private val mobileRepository: MobileAuthorization) {

    /**
     * Create new recording on db authentication*/
    suspend operator fun invoke(): MyResult<Boolean> = mobileRepository.signInWithPhone()
}