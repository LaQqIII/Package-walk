package com.example.packagewalk.data

/** Объект, который хранит данные текущего пользователя приложения */
object CurrentCargoruan {

    /** Номер телефона пользователя. */
    var phoneNumber: String? = null

    /** Имя пользователя. */
    var name: String? = null

    /** Признак пометки на удаление. */
    var markedForDeletion: Boolean = false

    /** Признак того, что пользователь уже идентифирован. */
    var identified: Boolean = false
}