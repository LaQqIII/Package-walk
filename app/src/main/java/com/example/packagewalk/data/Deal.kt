package com.example.packagewalk.data

/** Открытые сделки, лежат в бд в коллекции open_deals */
data class Deal(
    /** Уникальный идентификатор бд */
    val id: String = "",
    /** Признак того открыта данная сделка или нет */
    val isOpen: Boolean = false,
    /** Начальная точка (откуда забрать посылку) */
    val from: String = "",
    /** Конечная точка (куда отвезти посылку) */
    val to: String = "",
    /** Дата доставки (когда доставить посылку) */
    val data: String = "",
    /** Размер посылки */
    val size: Int = 0,
    /** Номер телефона заказчика */
    val phoneNumber: String = "",
    /** Стоимость сделки */
    val cost: Int = 0
)