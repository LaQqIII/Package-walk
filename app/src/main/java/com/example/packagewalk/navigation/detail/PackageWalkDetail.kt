package com.example.packagewalk.navigation.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.packagewalk.data.Deal
import com.example.packagewalk.ui.screens.deal.DealUI
import com.squareup.moshi.Moshi

fun NavGraphBuilder.addDetailSections(navHostController: NavHostController) {

    val actions = DetailActions(navHostController)

    composable(route = "${RoutesDetail.DEAL.route}/{deal}") { navBackStackEntry ->
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(Deal::class.java)
        DealUI(
            jsonAdapter.fromJson(navBackStackEntry.arguments?.getString("deal")!!),
            actions.backPressed
        )
    }

}