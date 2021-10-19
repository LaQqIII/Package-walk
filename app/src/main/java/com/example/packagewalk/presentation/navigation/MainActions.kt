package com.example.packagewalk.presentation.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.packagewalk.extensions.lifecycleIsResumed

class MainActions(navController: NavHostController) {
    val navigateToScreenAuthorization: (from: NavBackStackEntry) -> Unit = {
        if (it.lifecycleIsResumed()) {
            navController.navigate(AuthorizationSections.AUTHORIZATION.route) {
                if (navController.currentDestination?.route == MainSections.PROFILE.route) {
                    popUpTo(MainSections.PROGRESS.route) {
                        saveState = true
                    }
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
    val navigateToScreenProfile: () -> Unit = {
        navController.navigate(MainSections.PROFILE.route) {
            launchSingleTop = true
        }
    }
}