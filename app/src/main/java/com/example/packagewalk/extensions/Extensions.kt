package com.example.packagewalk.extensions

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.packagewalk.data.documents.Deal
import com.example.packagewalk.data.enums.DealStatus
import com.google.firebase.firestore.QueryDocumentSnapshot

fun Modifier.allPadding() = this.padding(16.dp)

fun Modifier.horizontalPadding() = this.padding(horizontal = 16.dp)

fun QueryDocumentSnapshot.toOpenDeal(): Deal {
    val deal = this.toObject(Deal::class.java)
    return deal.copy(id = this.id, status = DealStatus.OPEN)
}

fun QueryDocumentSnapshot.toCloseDeal(): Deal {
    val deal = this.toObject(Deal::class.java)
    return deal.copy(id = this.id, status = DealStatus.CLOSE)
}

fun QueryDocumentSnapshot.toCancelDeal(): Deal {
    val deal = this.toObject(Deal::class.java)
    return deal.copy(id = this.id, status = DealStatus.CANCEL)
}