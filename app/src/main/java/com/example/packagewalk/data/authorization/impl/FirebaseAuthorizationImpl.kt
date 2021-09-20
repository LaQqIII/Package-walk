package com.example.packagewalk.data.authorization.impl

import com.example.packagewalk.data.Result
import com.example.packagewalk.data.authorization.Authorization
import javax.inject.Inject

class FirebaseAuthorizationImpl @Inject constructor() : Authorization {

    override fun userLoggedIn(): Result<Boolean> {
        return Result.Success(false)
    }

}