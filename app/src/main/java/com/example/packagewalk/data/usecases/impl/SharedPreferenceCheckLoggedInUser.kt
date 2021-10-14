package com.example.packagewalk.data.usecases.impl

import com.example.packagewalk.data.Result
import com.example.packagewalk.data.success
import com.example.packagewalk.data.usecases.CheckLoggedInUser
import javax.inject.Inject

class SharedPreferenceCheckLoggedInUser
@Inject constructor() : CheckLoggedInUser {

    override suspend fun invoke(): Result<Boolean> {
        return success(false)
    }
}