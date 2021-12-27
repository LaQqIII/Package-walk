package com.example.packagewalk.navigation.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.packagewalk.data.Deal
import com.example.packagewalk.ui.screens.deal.DealUI

fun NavGraphBuilder.addDetailSections(navHostController: NavHostController) {

    val actions = DetailActions(navHostController)

    composable(
        route = "${RoutesDetail.DEAL.route}/{id}/{isOpen}/{from}/{to}/{data}/{size}/{phoneNumber}/{cost}",
        arguments = listOf(
            navArgument("isOpen") { NavType.StringType },
            navArgument("id") { NavType.StringType },
            navArgument("from") { NavType.StringType },
            navArgument("to") { NavType.StringType },
            navArgument("data") { NavType.StringType },
            navArgument("size") { NavType.StringType },
            navArgument("phoneNumber") { NavType.StringType },
            navArgument("cost") { NavType.StringType },
        )
    ) { navBackStackEntry ->
        DealUI(
            Deal(
                id = navBackStackEntry.arguments?.getString("id") ?: "",
                isOpen = navBackStackEntry.arguments?.getString("isOpen")?.toBoolean() ?: false,
                from = navBackStackEntry.arguments?.getString("from") ?: "",
                to = navBackStackEntry.arguments?.getString("to") ?: "",
                data = navBackStackEntry.arguments?.getString("data") ?: "",
                size = navBackStackEntry.arguments?.getString("size")?.toInt() ?: 0,
                phoneNumber = navBackStackEntry.arguments?.getString("phoneNumber") ?: "",
                cost = navBackStackEntry.arguments?.getString("cost")?.toInt() ?: 0
            ),
            actions.backPressed
        )
    }

}