package com.example.packagewalk.ui.screens.authorization.models

import androidx.lifecycle.MutableLiveData
import com.example.packagewalk.data.EventState

sealed class AuthorizationEvent(val state: MutableLiveData<EventState<Nothing>>) {
    object CheckAuthState : AuthorizationEvent(MutableLiveData(EventState.Create))
}
