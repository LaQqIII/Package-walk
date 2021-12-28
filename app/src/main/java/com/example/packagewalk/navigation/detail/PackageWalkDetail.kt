package com.example.packagewalk.navigation.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.packagewalk.navigation.SharingBetweenScreens
import com.example.packagewalk.ui.screens.deal.DealUI

fun NavGraphBuilder.addDetailSections(navHostController: NavHostController) {

    val actions = DetailActions(navHostController)

    composable(RoutesDetail.DEAL.route) {
        DealUI(
            deal = SharingBetweenScreens.deal,
            navigateToDeals = actions.navigateToDeals,
            backPressed = actions.backPressed
        )
    }

}