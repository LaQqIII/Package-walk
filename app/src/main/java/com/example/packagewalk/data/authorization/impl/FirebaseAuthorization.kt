package com.example.packagewalk.data.authorization.impl

import com.example.packagewalk.data.Result
import com.example.packagewalk.data.authorization.Authorization

class FirebaseAuthorization : Authorization {

    override fun userLoggedIn(): Result<Boolean> {
        return Result.Success(false)
    }

}