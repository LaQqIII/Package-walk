package com.example.packagewalk.ui.widgets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.packagewalk.data.documents.Deal

@Composable
fun ListDeals(
    deals: List<Deal>,
    navigateToDetail: (Deal) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(deals) { deal ->
            DealCard(deal = deal, onClick = { navigateToDetail(deal) })
        }
    }
}