package com.example.packagewalk.data

import java.lang.Exception

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

fun <T> success(data: T): Result.Success<T> = Result.Success(data)

fun err(exception: Exception): Result<Nothing> = Result.Error(exception)