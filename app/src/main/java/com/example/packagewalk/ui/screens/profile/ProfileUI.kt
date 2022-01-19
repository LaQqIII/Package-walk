package com.example.packagewalk.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.data.CurrentCargoruan
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.RowInfo

@Preview(showBackground = true)
@Composable
fun ProfileUI() {
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.around_base)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RowInfo(caption = R.string.what_your_name, value = CurrentCargoruan.name ?: "")
        RowInfo(caption = R.string.number_phone, value = CurrentCargoruan.phoneNumber ?: "")
        PackageWalkButton(
            stringId = R.string.logout,
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(dimensionResource(id = R.dimen.around_base))
        )
    }
}