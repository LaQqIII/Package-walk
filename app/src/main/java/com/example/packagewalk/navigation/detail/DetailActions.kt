package com.example.packagewalk.navigation.detail

import androidx.navigation.NavHostController
import com.example.packagewalk.navigation.main.MainSections

class DetailActions(navController: NavHostController) {
    val backPressed: () -> Unit = {
        navController.popBackStack()
    }
    val navigateToDeals: () -> Unit = {
        navController.navigate(MainSections.DEALS.route) {
            popUpTo(0)
            launchSingleTop = true
        }
    }
}