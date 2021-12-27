package com.example.packagewalk.ui.screens.new_deal.models

enum class NewDealEventState {
    /** Новая сделка добавлена в бд */
    CREATED,

    /** При добавлении новой сделки возникли ошибки, новая запись не создана */
    NOT_CREATED,

    /** Возникли какие-то ошибки */
    ERROR
}