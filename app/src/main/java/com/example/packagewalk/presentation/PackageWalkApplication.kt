package com.example.packagewalk.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.example.packagewalk.presentation.navigation.MainSections
import com.example.packagewalk.presentation.navigation.PackageWalkNavGraph
import com.example.packagewalk.presentation.theme.PackageWalkTheme
import com.example.packagewalk.presentation.widgets.PackageWalkBottomBar
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun PackageWalkApplication() {
    PackageWalkTheme {
        val navController = rememberNavController()
        val tabs = MainSections.values()
        Scaffold(bottomBar = { PackageWalkBottomBar(navController, tabs) }) {
            PackageWalkNavGraph(navController)
        }
    }
}