package com.example.packagewalk.ui.widgets

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.packagewalk.navigation.main.MainSections

/**
 * Отвечает за отрисовку и смену состояния нижней панели
 * @param tabs массив экранов, которые должны быть отрисованы в нижней панели
 */
@Composable
fun PackageWalkBottomBar(navController: NavController, tabs: Array<MainSections>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val sections = remember { MainSections.values() }
    val routes = remember { sections.map { it.route } }

    // Отрисовываем нижнюю панель только если пользователь находится на одном из основых экранов
    if (currentRoute in routes) {
        BottomNavigation {
            tabs.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = screen.icon, contentDescription = "") },
                    selected = screen.route == currentRoute,
                    onClick = {
                        if (screen.route != currentRoute) {
                            navController.navigate(screen.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(MainSections.Deals.route) {
                                    saveState = true
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}