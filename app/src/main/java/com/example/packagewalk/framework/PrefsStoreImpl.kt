package com.example.packagewalk.framework

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.packagewalk.core.data.PrefsStore
import com.example.packagewalk.extensions.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class PrefsStoreImpl
@Inject constructor(@ApplicationContext private val context: Context) : PrefsStore {

    override fun isUserLoggedIn(): Flow<Boolean> {
        Timber.d("!@# emit value userLoggedIn")
        return context.dataStore.data.catch { exception ->
            // dataStore.data throws an IOException if it can't read the data
            if (exception is IOException) { // 2
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[USER_LOGGED_IN_KEY] ?: false }
    }

    override suspend fun changeUserLoggedIn(flag: Boolean) {
        context.dataStore.edit {
            it[USER_LOGGED_IN_KEY] = flag
        }
    }

    companion object {
        private val USER_LOGGED_IN_KEY = booleanPreferencesKey("user_logged_in")
    }
}