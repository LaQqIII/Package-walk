package com.example.packagewalk.data.references

/** Класс, описывающий каргоруанца в приложении.
 * @param  phoneNumber - номер телефона каргоруанца
 * @param name - имя каргоруанца
 * @param markedForDeletion - признак пометки на удаление
 */
data class Сargoruan(
    val phoneNumber: String = "",
    val name: String = "",
    val markedForDeletion: Boolean = false
)
