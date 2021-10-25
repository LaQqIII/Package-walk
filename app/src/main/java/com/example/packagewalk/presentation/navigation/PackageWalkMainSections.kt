package com.example.packagewalk.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.packagewalk.R
import com.example.packagewalk.presentation.screens.createorder.CreateOrder
import com.example.packagewalk.presentation.screens.createorder.CreateOrderViewModel
import com.example.packagewalk.presentation.screens.profile.Profile
import com.example.packagewalk.presentation.screens.profile.ProfileViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
fun NavGraphBuilder.addMainSections(actions: Actions) {

    composable(MainSections.PROGRESS.route) {
        Text(text = "1")
    }

    composable(MainSections.FIND_ORDER.route) {
        Text(text = "2")
    }

    composable(MainSections.CREATE_ORDER.route) { from ->
        val viewModel = hiltViewModel<CreateOrderViewModel>()
        CreateOrder(
            viewModel = viewModel,
            navigateToScreenAuthorization = { actions.navigateToScreenAuthorization(from) }
        )
    }

    composable(MainSections.PROFILE.route) { from ->
        val viewModel = hiltViewModel<ProfileViewModel>()
        Profile(
            viewModel = viewModel,
            navigateToScreenAuthorization = { actions.navigateToScreenAuthorization(from) }
        )
    }
}

/**
 * Перечисленные объекты отображаются в нижней панели приложения и соответствуют основным экранам
 * @param title заголовок экрана
 * @param icon иконка, которая будет отображена в нижней панели
 * @param route маршрут до экрана
 */
enum class MainSections(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    PROGRESS(R.string.screen_progress, Icons.Outlined.Star, "progress"),
    FIND_ORDER(R.string.screen_find_order, Icons.Outlined.Search, "findOrder"),
    CREATE_ORDER(R.string.screen_create_order, Icons.Outlined.Create, "createOrder"),
    PROFILE(R.string.screen_profile, Icons.Outlined.Person, "profile")
}