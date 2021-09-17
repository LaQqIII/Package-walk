package com.example.packagewalk.data.authorization

import com.example.packagewalk.data.Result

interface Authorization {

    // todo вынеси в отдельный интерфейс, чтобы потом можно было удобно переделывать процедуру проверки
    fun userLoggedIn(): Result<Boolean>

}