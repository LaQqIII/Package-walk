package com.example.packagewalk.navigation.detail

import androidx.navigation.NavHostController

class DetailActions(navController: NavHostController) {
    val backPressed: () -> Unit = {
        navController.popBackStack()
    }
}