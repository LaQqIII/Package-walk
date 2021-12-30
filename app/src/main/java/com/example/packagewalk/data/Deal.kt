package com.example.packagewalk.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Deal : Parcelable {
    @Parcelize
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
        val cost: Int = 0,
        /** Имя заказчика */
        val customer: String = ""
    ) : Parcelable, Deal()

    @Parcelize
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
        val cost: Int = 0,
        /** Имя заказчика */
        val customer: String = "",
        /** Имя исполнителя */
        val executor: String = ""
    ) : Parcelable, Deal()
}