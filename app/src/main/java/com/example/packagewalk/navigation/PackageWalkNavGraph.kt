package com.example.packagewalk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.packagewalk.navigation.detail.addDetailSections
import com.example.packagewalk.navigation.main.MainSections
import com.example.packagewalk.navigation.main.addMainSections

@Composable
fun PackageWalkNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainSections.Deals.route
    ) {
        addMainSections(navController)
        addDetailSections()
    }
}