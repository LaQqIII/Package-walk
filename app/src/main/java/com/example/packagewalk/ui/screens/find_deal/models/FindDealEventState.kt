package com.example.packagewalk.ui.screens.find_deal.models

enum class FindDealEventState {
    /** Выполняется поиск сделок, соответствующих введеным условиям */
    LOADING,

    /** Сделки загружен, отображаются пользователю */
    LOADED,

    /** По заданным условиям не найдено ни одной сделки */
    EMPTY,

    /** Возникли какие-то ошибки */
    ERROR
}