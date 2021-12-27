package com.example.packagewalk.data

sealed class Deal {
    /** Открытые сделки, лежат в бд в коллекции open_deals */
    data class OpenDeal(
        /** Уникальный идентификатор бд */
        val id: String = "",
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
    ) : Deal()

    /** Закрытые сделки, лежат в бд в коллекции close_deals */
    data class CloseDeal(
        /** Уникальный идентификатор бд */
        val id: String = "",
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
    ) : Deal()
}