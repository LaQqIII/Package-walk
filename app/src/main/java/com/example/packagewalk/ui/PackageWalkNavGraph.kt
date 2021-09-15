package com.example.packagewalk.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun PackageWalkNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "progress"
    ) {
        composable(Screens.PROGRESS.route) {
            Text(text = "1")
        }
        composable(Screens.FIND_ORDER.route) {
            Text(text = "2")
        }
        composable(Screens.CREATE_ORDER.route) {
            Text(text = "3")
        }
        composable(Screens.PROFILE.route) {
            Text(text = "4")
        }
    }
}