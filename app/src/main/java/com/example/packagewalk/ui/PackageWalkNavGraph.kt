package com.example.packagewalk.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.packagewalk.ui.screens.authorization.Authorization
import com.example.packagewalk.ui.screens.authorization.mobileAuth.MobileAuthorizationScreen
import com.example.packagewalk.ui.screens.authorization.mobileAuth.MobileAuthorizationViewModel
import com.example.packagewalk.ui.screens.authorization.registration.RegistrationScreen
import com.example.packagewalk.ui.screens.profile.Profile

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
            MobileAuthorizationScreen(navigateBack = actions.navigateBack, viewModel = viewModel)
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
}