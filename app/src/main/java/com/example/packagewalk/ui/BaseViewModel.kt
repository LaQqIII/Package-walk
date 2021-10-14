package com.example.packagewalk.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val failure = mutableStateOf("")
}