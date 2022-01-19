package com.example.packagewalk.data

/** Объект, который хранит данные текущего пользователя приложения */
object CurrentCargoruan {
    /** Уникальный идентификатор, который соответствует пути к User ID в бд. */
    var uid: String? = null

    /** Номер телефона пользователя. */
    var phoneNumber: String? = null

    /** Имя пользователя. */
    var name: String? = null

    /** Признак пометки на удаление. */
    var markedForDeletion: Boolean = false

    /** Признак того, что пользователь уже идентифирован. */
    var identified: Boolean = false
}