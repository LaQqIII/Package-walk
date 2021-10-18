package com.example.packagewalk.core.interactors

import com.example.packagewalk.core.data.PrefsStore
import javax.inject.Inject

class ChangeLoggedInUser
@Inject constructor(private val dataSource: PrefsStore) {

    suspend operator fun invoke(flag: Boolean) {
        dataSource.changeUserLoggedIn(flag)
    }
}