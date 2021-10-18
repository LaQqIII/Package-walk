package com.example.packagewalk.core.interactors

import com.example.packagewalk.core.data.PrefsStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckLoggedInUser
@Inject constructor(private val dataSource: PrefsStore) {

    operator fun invoke(): Flow<Boolean> = dataSource.isUserLoggedIn()
}