package com.example.packagewalk.ui.screens.authorization.models

enum class AuthorizationEventState {
    CREATE,
    CODE_SEND,
    CODE_NOT_SEND,
    CODE_CORRECT,
    USER_LOGIN,
    USER_FAILED_LOGIN
}