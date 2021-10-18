package com.example.packagewalk.core.data

import kotlinx.coroutines.flow.Flow

interface PrefsStore {

    fun isUserLoggedIn(): Flow<Boolean>

    suspend fun changeUserLoggedIn(flag: Boolean)
}