package com.example.packagewalk.ui.screens.authorization.mobileAuth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MobileAuthorizationViewModel @Inject constructor() : ViewModel() {

    val phoneNumber = mutableStateOf("")

}