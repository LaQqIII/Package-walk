package com.example.packagewalk.ui.screens.profile

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.packagewalk.ui.screens.authorization.AuthorizationUI

@Composable
fun ProfileUI() {
    AuthorizationUI {
        Text(text = "4")
    }
}