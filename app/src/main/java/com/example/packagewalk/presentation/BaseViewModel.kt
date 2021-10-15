package com.example.packagewalk.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val failure = mutableStateOf("")
}