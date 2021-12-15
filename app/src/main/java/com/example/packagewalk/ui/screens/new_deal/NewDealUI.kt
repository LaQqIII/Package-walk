package com.example.packagewalk.ui.screens.new_deal

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.packagewalk.ui.screens.authorization.AuthorizationUI

@Composable
fun NewDealUI() {
    AuthorizationUI {
        Text(text = "3")
    }
}