package com.example.packagewalk.ui

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.example.packagewalk.R
import com.example.packagewalk.ui.theme.PackageWalkTheme

enum class Screens(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    PROGRESS(R.string.screen_progress, Icons.Outlined.Star, "progress"),
    FIND_ORDER(R.string.screen_find_order, Icons.Outlined.Search, "findOrder"),
    CREATE_ORDER(R.string.screen_create_order, Icons.Outlined.Create, "createOrder"),
    PROFILE(R.string.screen_profile, Icons.Outlined.Person, "profile")
}

@Composable
fun PackageWalkApplication() {
    PackageWalkTheme {

        val navController = rememberNavController()
        val tabs = Screens.values()

        Scaffold(bottomBar = { PackageWalkBottomBar(navController, tabs) }) {
            PackageWalkNavGraph(navController)
        }
    }
}