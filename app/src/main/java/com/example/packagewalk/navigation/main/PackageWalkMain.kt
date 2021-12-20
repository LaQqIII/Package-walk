package com.example.packagewalk.navigation.main

import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.packagewalk.ui.screens.find_deal.FindDealUI
import com.example.packagewalk.ui.screens.new_deal.NewDealUI
import com.example.packagewalk.ui.screens.profile.ProfileUI

fun NavGraphBuilder.addMainSections(navHostController: NavHostController) {

    composable(MainSections.Deals.route) {
        Text(text = "1")
    }

    composable(MainSections.FIND_DEAL.route) {
        FindDealUI()
    }

    composable(MainSections.NEW_DEAL.route) {
        NewDealUI()
    }

    composable(MainSections.PROFILE.route) {
        ProfileUI()
    }

}