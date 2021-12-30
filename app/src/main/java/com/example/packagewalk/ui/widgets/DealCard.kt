package com.example.packagewalk.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.packagewalk.R
import com.example.packagewalk.data.Deal
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.text.TextSubtitle1

@Composable
fun DealCard(deal: Deal, onClick: () -> Unit) {
    when (deal) {
        is Deal.OpenDeal -> DealCard(deal = deal, onClick = onClick)
        is Deal.CloseDeal -> DealCard(deal = deal, onClick = onClick)
    }
}

@Composable
private fun DealCard(deal: Deal.OpenDeal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextSubtitle1(stringResource(id = R.string.open))
                TextSubtitle1(
                    value = "${deal.cost} руб.",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
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

@Composable
private fun DealCard(deal: Deal.CloseDeal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextSubtitle1(stringResource(id = R.string.close))
                TextSubtitle1(
                    value = "${deal.cost} руб.",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
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
private fun OpenDealPreview() {
    PackageWalkTheme {
        DealCard(
            deal = Deal.OpenDeal(
                from = "Саров",
                to = "Нижний довгород",
                data = "30.12.2021",
                cost = 100
            )
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CloseDealPreview() {
    PackageWalkTheme {
        DealCard(
            deal = Deal.CloseDeal(
                from = "Саров",
                to = "Нижний довгород",
                data = "30.12.2021",
                cost = 100
            )
        ) {

        }
    }
}