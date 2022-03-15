package com.example.packagewalk.ui.screens.authorization.models

enum class AuthorizationEventState {
    /** Инициаилизация экрана */
    CREATE,

    /** Код отправлен */
    CODE_SEND,

    /** Код не отправлен */
    CODE_NOT_SEND,

    /** Введенный код соответствует отправленному  */
    CODE_CORRECT,

    /** Пользователь залогинился */
    USER_LOGIN,

    /** Пользователь не смог залогиниться */
    USER_FAILED_LOGIN
}