package com.example.packagewalk.core.interactors

import com.example.packagewalk.core.data.MobileAuthorization
import com.example.packagewalk.core.domain.MyResult
import javax.inject.Inject

class SignInWithPhone
@Inject constructor(private val mobileRepository: MobileAuthorization) {
    /**
     * Create new recording on db authentication
     */
    suspend operator fun invoke(): MyResult<Boolean> = mobileRepository.signInWithPhone()
}