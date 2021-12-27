package com.example.packagewalk.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.packagewalk.R
import com.example.packagewalk.data.Deal
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.text.TextSubtitle1

@Composable
fun RowDeal(deal: Deal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            TextSubtitle1(
                value = if (deal.isOpen)
                    stringResource(id = R.string.open)
                else
                    stringResource(id = R.string.close),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row {
                TextSubtitle1(value = deal.from)
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = ""
                )
                TextSubtitle1(value = deal.to)
            }
            TextSubtitle1(value = deal.data, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PackageWalkTheme {
        RowDeal(Deal()) {}
    }
}