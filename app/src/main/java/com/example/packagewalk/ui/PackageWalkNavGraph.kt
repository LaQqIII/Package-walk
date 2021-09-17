package com.example.packagewalk.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.packagewalk.ui.screens.authorization.Authorization
import com.example.packagewalk.ui.screens.profile.Profile

@Composable
fun PackageWalkNavGraph(navController: NavHostController) {

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = Screens.PROGRESS.route
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
            Profile(navigateToScreenAuthorization = actions.navigateToScreenAuthorization)
        }
        composable("authorization") {
            Authorization()
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToScreenAuthorization: () -> Unit = {
        navController.navigate("authorization")
    }
}