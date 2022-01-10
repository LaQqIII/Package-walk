package com.example.packagewalk.data.enums

/** Статус документа сделки */
enum class DealStatus(val description: String) {
    UNKNOWN(""),
    OPEN("Выполняется"),
    CLOSE("Доставлена"),
    CANCEL("Отменена")
}