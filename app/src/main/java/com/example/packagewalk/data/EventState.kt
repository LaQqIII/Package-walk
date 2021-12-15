package com.example.packagewalk.data

sealed class EventState<out T> {
    object Create : EventState<Nothing>()
    object Loading : EventState<Nothing>()
    data class Success<out T>(val data: T) : EventState<T>()
    data class Error(val exception: Exception) : EventState<Nothing>()
}
