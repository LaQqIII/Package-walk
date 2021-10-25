package com.example.packagewalk.presentation.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.packagewalk.presentation.screens.authorization.Authorization
import com.example.packagewalk.presentation.screens.authorization.mobileAuth.EnterCodeScreen
import com.example.packagewalk.presentation.screens.authorization.mobileAuth.EnterCodeViewModel
import com.example.packagewalk.presentation.screens.authorization.mobileAuth.MobileAuthorizationScreen
import com.example.packagewalk.presentation.screens.authorization.mobileAuth.MobileAuthorizationViewModel
import com.example.packagewalk.presentation.screens.authorization.registration.RegistrationScreen
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.addAuthorizationSections(actions: Actions) {
    composable(AuthorizationSections.AUTHORIZATION.route) {
        Authorization(
            navigateToScreenLoginIn = actions.navigateToScreenLoginIn,
            navigateToScreenRegistration = actions.navigateToScreenRegistration,
            navigateBack = actions.navigateBack
        )
    }
    composable(AuthorizationSections.LOGIN.route) {

    }
    composable(AuthorizationSections.REGISTRATION.route) {
        RegistrationScreen(
            navigateBack = actions.navigateBack,
            navigateToScreenMobileAuthorization = actions.navigateToScreenMobileAuthorization
        )
    }
    composable(AuthorizationSections.MOBILE_AUTHORIZATION.route) {
        val viewModel = hiltViewModel<MobileAuthorizationViewModel>()
        MobileAuthorizationScreen(
            navigateBack = actions.navigateBack,
            viewModel = viewModel,
            navigateToScreenEnterCode = actions.navigateToScreenEnterCode
        )
    }
    composable(
        "${AuthorizationSections.ENTER_CODE.route}/{phoneNumber}",
        arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType })
    ) { backStackEntry ->
        val viewModel = hiltViewModel<EnterCodeViewModel>()
        EnterCodeScreen(
            viewModel = viewModel,
            phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: "",
            navigateBack = actions.navigateBack,
            navigateToScreenProfile = actions.navigateToScreenProfile
        )
    }
}

/**
 * Экраны, необходимые для процедуры авторизации пользователя в системе
 * @param route маршрут до экрана
 */
enum class AuthorizationSections(val route: String) {
    AUTHORIZATION("authorization"),
    LOGIN("loginIn"),
    REGISTRATION("registration"),
    MOBILE_AUTHORIZATION("mobile_authorization"),
    ENTER_CODE("enter_code")
}