package com.example.packagewalk.navigation.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.packagewalk.R

/**
 * Перечисленные объекты отображаются в нижней панели приложения и соответствуют основным экранам
 * @param title заголовок экрана
 * @param icon иконка, которая будет отображена в нижней панели
 * @param route маршрут до экрана
 */
enum class MainSections(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    Deals(R.string.screen_progress, Icons.Outlined.Star, "deals"),
    FIND_DEAL(R.string.screen_find_order, Icons.Outlined.Search, "find_deal"),
    NEW_DEAL(R.string.screen_create_order, Icons.Outlined.Create, "new_deal"),
    PROFILE(R.string.screen_profile, Icons.Outlined.Person, "profile")
}