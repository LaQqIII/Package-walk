package com.example.packagewalk.navigation.authorization

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.addAuthorizationSections(navHostController: NavHostController) {
    val actions = AuthorizationActions(navHostController)
//    composable(RoutesAuthorization.RoutesAUTHORIZATION.route) {
//        Authorization(
//            navigateToScreenLoginIn = actions.navigateToScreenLoginIn,
//            navigateToScreenRegistration = actions.navigateToScreenRegistration,
//            navigateBack = actions.navigateBack
//        )
//    }
//    composable(RoutesAuthorization.LOGIN.route) {
//
//    }
//    composable(RoutesAuthorization.REGISTRATION.route) {
//        RegistrationScreen(
//            navigateBack = actions.navigateBack,
//            navigateToScreenMobileAuthorization = actions.navigateToScreenMobileAuthorization
//        )
//    }
//    composable(RoutesAuthorization.MOBILE_Routes_AUTHORIZATION.route) {
//        val viewModel = hiltViewModel<MobileAuthorizationViewModel>()
//        MobileAuthorizationScreen(
//            navigateBack = actions.navigateBack,
//            viewModel = viewModel,
//            navigateToScreenEnterCode = actions.navigateToScreenEnterCode
//        )
//    }
//    composable(
//        "${RoutesAuthorization.ENTER_CODE.route}/{phoneNumber}",
//        arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType })
//    ) { backStackEntry ->
//        val viewModel = hiltViewModel<EnterCodeViewModel>()
//        EnterCodeScreen(
//            viewModel = viewModel,
//            phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: "",
//            navigateBack = actions.navigateBack,
//            navigateToScreenProfile = actions.navigateToScreenProfile
//        )
//    }
}