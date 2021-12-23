package com.example.packagewalk.navigation.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.packagewalk.ui.screens.deal.DealUI

fun NavGraphBuilder.addDetailSections() {

    composable(RoutesDetail.DEAL.route) {
        DealUI()
    }

}