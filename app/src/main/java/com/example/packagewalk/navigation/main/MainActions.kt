package com.example.packagewalk.navigation.main

import androidx.navigation.NavHostController

class MainActions(navController: NavHostController) {
//    val navigateToScreenAuthorization: (from: NavBackStackEntry) -> Unit = {
//        if (it.lifecycleIsResumed()) {
//            navController.navigate(AuthorizationSections.AUTHORIZATION.route) {
//                val currentRoute = navController.currentDestination?.route
//                if (currentRoute == MainSections.PROFILE.route
//                    || currentRoute == MainSections.CREATE_ORDER.route
//                ) {
//                    popUpTo(MainSections.PROGRESS.route) {
//                        saveState = true
//                    }
//                }
//            }
//        }
//    }
//    val navigateToScreenLoginIn: () -> Unit = {
//        navController.navigate(AuthorizationSections.LOGIN.route)
//    }
//    val navigateToScreenRegistration: () -> Unit = {
//        navController.navigate(AuthorizationSections.REGISTRATION.route)
//    }
//    val navigateBack: () -> Unit = {
//        navController.popBackStack()
//    }
//    val navigateToScreenMobileAuthorization: () -> Unit = {
//        navController.navigate(AuthorizationSections.MOBILE_AUTHORIZATION.route)
//    }
//    val navigateToScreenEnterCode: (String) -> Unit = { phoneNumber ->
//        navController.navigate("${AuthorizationSections.ENTER_CODE.route}/${"+7$phoneNumber"}")
//    }
//    val navigateToScreenProfile: () -> Unit = {
//        navController.navigate(MainSections.PROFILE.route) {
//            launchSingleTop = true
//        }
//    }
}