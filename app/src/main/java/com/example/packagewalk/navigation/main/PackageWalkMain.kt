package com.example.packagewalk.navigation.main

import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.packagewalk.R
import com.example.packagewalk.ui.screens.deals.DealsUI
import com.example.packagewalk.ui.screens.find_deal.FindDealUI
import com.example.packagewalk.ui.screens.new_deal.NewDealUI
import com.example.packagewalk.ui.screens.profile.ProfileUI
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun NavGraphBuilder.addMainSections(navHostController: NavHostController) {

    val actions = MainActions(navHostController)

    composable(MainSections.DEALS.route) {
        DealsUI(
            navigateToDeal = actions.navigateToDeal,
            navigateToNewDeal = actions.navigateToNewDeal,
            navigateToFindDeal = actions.navigateToFindDeal
        )
    }

    composable(MainSections.FIND_DEAL.route) {
        Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.screen_find_order) }) {
            FindDealUI(navigateToDeal = actions.navigateToOpenDeal)
        }
    }

    composable(MainSections.NEW_DEAL.route) {
        Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.new_order) }) {
            NewDealUI(navigateToDeals = actions.navigateToDeals)
        }
    }

    composable(MainSections.PROFILE.route) {
        Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.screen_profile) }) {
            ProfileUI()
        }
    }

}