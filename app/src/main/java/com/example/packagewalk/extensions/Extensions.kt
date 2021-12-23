package com.example.packagewalk.extensions

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry

/**
 * Если жизненный цикл не возобновлен, это означает, что этот NavBackStackEntry уже обработал событие навигации.
 *
 * Это используется для дедупликации событий навигации.
 */
fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

fun Modifier.allPadding() = this.padding(56.dp)