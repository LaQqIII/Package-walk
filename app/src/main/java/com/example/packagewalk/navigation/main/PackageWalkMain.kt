package com.example.packagewalk.navigation.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.packagewalk.ui.screens.deals.DealsUI
import com.example.packagewalk.ui.screens.find_deal.FindDealUI
import com.example.packagewalk.ui.screens.new_deal.NewDealUI
import com.example.packagewalk.ui.screens.profile.ProfileUI

fun NavGraphBuilder.addMainSections(navHostController: NavHostController) {

    val actions = MainActions(navHostController)

    composable(MainSections.Deals.route) {
        DealsUI(navigateToDetail = actions.navigateToDetail)
    }

    composable(MainSections.FIND_DEAL.route) {
        FindDealUI(navigateToDetail = actions.navigateToDetail)
    }

    composable(MainSections.NEW_DEAL.route) {
        NewDealUI()
    }

    composable(MainSections.PROFILE.route) {
        ProfileUI()
    }

}