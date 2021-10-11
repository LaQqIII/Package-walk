package com.example.packagewalk.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.packagewalk.ui.screens.authorization.Authorization
import com.example.packagewalk.ui.screens.authorization.mobileAuth.EnterCodeScreen
import com.example.packagewalk.ui.screens.authorization.mobileAuth.EnterCodeViewModel
import com.example.packagewalk.ui.screens.authorization.mobileAuth.MobileAuthorizationScreen
import com.example.packagewalk.ui.screens.authorization.mobileAuth.MobileAuthorizationViewModel
import com.example.packagewalk.ui.screens.authorization.registration.RegistrationScreen
import com.example.packagewalk.ui.screens.profile.Profile

@ExperimentalComposeUiApi
@Composable
fun PackageWalkNavGraph(navController: NavHostController) {

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = MainSections.PROGRESS.route
    ) {
        composable(MainSections.PROGRESS.route) {
            Text(text = "1")
        }
        composable(MainSections.FIND_ORDER.route) {
            Text(text = "2")
        }
        composable(MainSections.CREATE_ORDER.route) {
            Text(text = "3")
        }
        composable(MainSections.PROFILE.route) {
            Profile(navigateToScreenAuthorization = actions.navigateToScreenAuthorization)
        }
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
                navigateBack = actions.navigateBack,
                phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToScreenAuthorization: () -> Unit = {
        navController.navigate(AuthorizationSections.AUTHORIZATION.route) {
            if (navController.currentDestination?.route == MainSections.PROFILE.route) {
                popUpTo(MainSections.PROGRESS.route) {
                    saveState = true
                }
            }
        }
    }
    val navigateToScreenLoginIn: () -> Unit = {
        navController.navigate(AuthorizationSections.LOGIN.route)
    }
    val navigateToScreenRegistration: () -> Unit = {
        navController.navigate(AuthorizationSections.REGISTRATION.route)
    }
    val navigateBack: () -> Unit = {
        navController.popBackStack()
    }
    val navigateToScreenMobileAuthorization: () -> Unit = {
        navController.navigate(AuthorizationSections.MOBILE_AUTHORIZATION.route)
    }
    val navigateToScreenEnterCode: (String) -> Unit = { phoneNumber ->
        navController.navigate("${AuthorizationSections.ENTER_CODE.route}/${"+7$phoneNumber"}")
    }
}