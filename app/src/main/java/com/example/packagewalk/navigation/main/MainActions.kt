package com.example.packagewalk.navigation.main

import androidx.navigation.NavHostController
import com.example.packagewalk.navigation.detail.RoutesDetail

class MainActions(navController: NavHostController) {
    val navigateToDetail: () -> Unit = {
        navController.navigate(RoutesDetail.DEAL.route)
    }
}