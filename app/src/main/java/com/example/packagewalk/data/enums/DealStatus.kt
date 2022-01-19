package com.example.packagewalk.data.enums

/** Статус документа сделки */
enum class DealStatus(val description: String) {
    /** Состояние сделки по умолчанию */
    UNKNOWN("Неизвестный"),

    /** Сделка в процессе выполнения */
    OPEN("Выполняется"),

    /** Сделку закрыли(посылку доставили) */
    CLOSE("Доставлена"),

    /** Сделка отменена */
    CANCEL("Отменена"),

    /** Сделка помечена на удаление */
    DELETE("Помечен на удаление")
}