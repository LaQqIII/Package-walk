package com.example.packagewalk.navigation.main

import androidx.compose.material.Text
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.addMainSections(navHostController: NavHostController) {

    composable(MainSections.PROGRESS.route) {
        Text(text = "1")
    }

    composable(MainSections.FIND_ORDER.route) {
        Text(text = "2")
    }

    composable(MainSections.CREATE_ORDER.route) {
    }

    composable(MainSections.PROFILE.route) {
    }
}