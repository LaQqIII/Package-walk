package com.example.packagewalk.navigation.main

import androidx.navigation.NavHostController
import com.example.packagewalk.data.documents.Deal
import com.example.packagewalk.navigation.SharingBetweenScreens
import com.example.packagewalk.navigation.detail.RoutesDetail

class MainActions(navController: NavHostController) {
    val navigateToDeal: (Deal) -> Unit = {
        SharingBetweenScreens.deal = it
        navController.navigate(RoutesDetail.DEAL.route)
    }
    val navigateToNewDeal: () -> Unit = {
        navController.navigate(MainSections.NEW_DEAL.route) {
            popUpTo(0)
            launchSingleTop = true
        }
    }
    val navigateToFindDeal: () -> Unit = {
        navController.navigate(MainSections.FIND_DEAL.route) {
            popUpTo(0)
            launchSingleTop = true
        }
    }
    val navigateToDeals: () -> Unit = {
        navController.navigate(MainSections.DEALS.route) {
            popUpTo(0)
            launchSingleTop = true
        }
    }
}