package com.example.packagewalk.data.documents

import com.example.packagewalk.data.enums.DealStatus
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

/** Документ сделка, содержит всю информацию о заказе */
data class Deal(
    /** Уникальный идентификатор бд */
    val id: String = "",
    /** Дата создания документа */
    @ServerTimestamp val dateOfCreation: Date? = null,
    /** Дата закрытия документа */
    @ServerTimestamp val closingDate: Date? = null,
    /** Статус документа сделки */
    val status: DealStatus = DealStatus.UNKNOWN,
    /** Начальная точка (откуда забрать посылку) */
    val from: String = "",
    /** Конечная точка (куда отвезти посылку) */
    val to: String = "",
    /** Дата доставки (когда доставить посылку) */
    val data: String = "",
    /** Размер посылки */
    val size: Int = 0,
    /** Стоимость сделки */
    val cost: Int = 0,
    /** Признак пометки на удаление */
    val markedForDeletion: Boolean = false,
    /** Имя заказчика */
    val customerName: String = "",
    /** Номер телефона заказчика */
    val customerPhoneNumber: String = "",
)