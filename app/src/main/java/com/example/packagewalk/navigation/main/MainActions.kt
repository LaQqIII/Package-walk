package com.example.packagewalk.navigation.main

import androidx.navigation.NavHostController
import com.example.packagewalk.data.Deal
import com.example.packagewalk.navigation.detail.RoutesDetail

class MainActions(navController: NavHostController) {
    val navigateToDetail: (Deal) -> Unit = {
        navController.navigate(
            "${RoutesDetail.DEAL.route}/" +
                    "${it.id}/" +
                    "${it.isOpen}/" +
                    "${it.from}/" +
                    "${it.to}/" +
                    "${it.data}/" +
                    "${it.size}/" +
                    "${it.phoneNumber}/" +
                    "${it.cost}"
        )
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