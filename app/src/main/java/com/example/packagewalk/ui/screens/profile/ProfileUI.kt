package com.example.packagewalk.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.ui.screens.authorization.AuthorizationUI
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.RowInfo

@Preview(showBackground = true)
@Composable
fun ProfileUI() {
//    AuthorizationUI {
    Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.screen_profile) }) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.around_base)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RowInfo(caption = R.string.what_your_name, value = "Vlad")
            RowInfo(caption = R.string.number_phone, value = "+79809216265")
            PackageWalkButton(
                stringId = R.string.logout,
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.around_base))
            )
        }
    }
//    }
}