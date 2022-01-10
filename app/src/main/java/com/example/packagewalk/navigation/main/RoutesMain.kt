package com.example.packagewalk.navigation.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DriveEta
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Send
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
    DEALS(R.string.screen_progress, Icons.Outlined.Star, "deals"),
    NEW_DEAL(R.string.screen_create_order, Icons.Outlined.Send, "new_deal"),
    FIND_DEAL(R.string.screen_find_order, Icons.Outlined.DriveEta, "find_deal"),
    PROFILE(R.string.screen_profile, Icons.Outlined.Person, "profile")
}