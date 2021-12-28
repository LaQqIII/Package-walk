package com.example.packagewalk.extensions

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.example.packagewalk.data.Deal
import com.google.firebase.firestore.QueryDocumentSnapshot

/**
 * Если жизненный цикл не возобновлен, это означает, что этот NavBackStackEntry уже обработал событие навигации.
 *
 * Это используется для дедупликации событий навигации.
 */
fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

fun Modifier.allPadding() = this.padding(16.dp)

fun Modifier.horizontalPadding() = this.padding(horizontal = 16.dp)

fun QueryDocumentSnapshot.toOpenDeal(): Deal.OpenDeal {
    val deal = this.toObject(Deal.OpenDeal::class.java)
    return deal.copy(id = this.id)
}

fun QueryDocumentSnapshot.toCloseDeal(): Deal.CloseDeal {
    val deal = this.toObject(Deal.CloseDeal::class.java)
    return deal.copy(id = this.id)
}

fun NavController.navigate(
    route: String,
    param: Pair<String, Parcelable>?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    param?.let { this.currentBackStackEntry?.arguments?.putParcelable(param.first, param.second) }
    navigate(route, builder)
}

fun NavController.navigate(
    route: String,
    params: List<Pair<String, Parcelable>>?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    params?.let {
        val arguments = this.currentBackStackEntry?.arguments
        params.forEach { arguments?.putParcelable(it.first, it.second) }
    }
    navigate(route, builder)
}

fun NavController.navigate(
    route: String,
    params: Bundle?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this.currentBackStackEntry?.arguments?.putAll(params)
    navigate(route, builder)
}