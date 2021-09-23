package com.example.packagewalk.ui.screens.authorization

import androidx.lifecycle.ViewModel
import com.example.packagewalk.data.authorization.Authorization
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val repository: Authorization
) : ViewModel() {

}