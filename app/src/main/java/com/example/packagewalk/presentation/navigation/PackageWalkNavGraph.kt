package com.example.packagewalk.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun PackageWalkNavGraph(navController: NavHostController) {

    val actions = Actions(navController)

    NavHost(
        navController = navController,
        startDestination = MainSections.PROGRESS.route
    ) {
        addMainSections(actions)

        addAuthorizationSections(actions)
    }
}