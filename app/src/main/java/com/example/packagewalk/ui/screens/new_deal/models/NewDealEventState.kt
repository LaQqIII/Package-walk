package com.example.packagewalk.ui.screens.new_deal.models

enum class NewDealEventState {
    /** Инициаилизация экрана */
    START,

    /** Выполняется процедура по добавлению новой сделки в бд */
    CREATE,

    /** Новая сделка добавлена в бд */
    CREATED,

    /** При добавлении новой сделки возникли ошибки, новая запись не создана */
    NOT_CREATED,

    /** Возникли какие-то ошибки */
    ERROR
}