package com.example.packagewalk.ui

import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.*
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import timber.log.Timber

@Composable
fun PackageWalkBottomBar(navController: NavController, tabs: Array<Screens>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation {
        tabs.forEach { screen ->
            BottomNavigationItem(
                icon = screen.icon,
                selected = screen.route == currentRoute,
                onClick = {
                    if (screen.route != currentRoute) {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo("progress") {
                                saveState = true
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun BottomNavigationItem(
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Icon(imageVector = icon, contentDescription = "", modifier = Modifier.clickable { onClick() })
}