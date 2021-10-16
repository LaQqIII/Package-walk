package com.example.packagewalk.core.domain

import java.lang.Exception

sealed class MyResult<out T> {
    data class Success<out T>(val data: T) : MyResult<T>()
    data class Error(val exception: Exception) : MyResult<Nothing>()
}

fun <T> success(data: T): MyResult.Success<T> = MyResult.Success(data)

fun err(exception: Exception): MyResult<Nothing> = MyResult.Error(exception)