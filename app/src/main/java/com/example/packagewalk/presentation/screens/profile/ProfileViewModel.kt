package com.example.packagewalk.presentation.screens.profile

import com.example.packagewalk.framework.Interactors
import com.example.packagewalk.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(private val interactors: Interactors) : BaseViewModel() {

    val userLoggedIn = interactors.checkLoggedInUser()
}