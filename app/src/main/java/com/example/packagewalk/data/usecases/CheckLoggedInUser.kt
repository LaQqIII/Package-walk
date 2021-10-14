package com.example.packagewalk.data.usecases

import com.example.packagewalk.data.Result

interface CheckLoggedInUser {
    /**
     * Should be return Result.Success when there were no exceptions
     * Result.Success(true) - when user logged in
     * Result.Success(false) - when user not logged on
     * Result.Error - when was exceptions
     */
    suspend operator fun invoke(): Result<Boolean>
}