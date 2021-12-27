package com.example.packagewalk.ui.screens.deals.models

enum class DealsEventState {
    /** Инициаилизация экрана */
    CREATE,

    /** Выполняется загрузка всех сделок пользователя */
    LOADING,

    /** Сделки загружены, отображается результат */
    LOADED,

    /** Пришел пустой список сделок */
    EMPTY,

    /** Возникли какие-то ошибки */
    ERROR
}