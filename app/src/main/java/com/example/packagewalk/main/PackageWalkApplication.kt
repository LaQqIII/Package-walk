package com.example.packagewalk.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.packagewalk.navigation.PackageWalkNavGraph
import com.example.packagewalk.navigation.main.MainSections
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.PackageWalkBottomBar

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