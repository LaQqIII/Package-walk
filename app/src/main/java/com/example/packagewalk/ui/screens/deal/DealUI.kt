package com.example.packagewalk.ui.screens.deal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.extensions.allPadding
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.RowInfo

@Preview(showBackground = true)
@Composable
fun DealUI() {
    Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.deal) }) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.around_base)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RowInfo(caption = R.string.from, value = "Саров")
            RowInfo(caption = R.string.to, value = "Нижний Новогород")
            RowInfo(caption = R.string.whenn, value = "21.12.2021")
            PackageWalkButton(
                stringId = R.string.take_deal,
                onClick = { /*TODO*/ },
                modifier = Modifier.allPadding()
            )
        }
    }
}