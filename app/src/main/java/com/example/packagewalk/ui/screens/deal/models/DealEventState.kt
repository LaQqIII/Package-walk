package com.example.packagewalk.ui.screens.deal.models

enum class DealEventState {
    /** Сделка закрыта успешно */
    CLOSE_DEAL,

    /** Сделка отменена успешно */
    CANCEL_DEAL,

    /** Возникли какие-то ошибки, показывается соответсвующий экран */
    ERROR
}