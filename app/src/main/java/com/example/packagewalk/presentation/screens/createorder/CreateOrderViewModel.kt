package com.example.packagewalk.presentation.screens.createorder

import androidx.compose.runtime.mutableStateOf
import com.example.packagewalk.framework.Interactors
import com.example.packagewalk.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateOrderViewModel
@Inject constructor(interactors: Interactors) : BaseViewModel() {

    val userLoggedIn = interactors.checkLoggedInUser()

    val from = mutableStateOf("")

    val to = mutableStateOf("")
}