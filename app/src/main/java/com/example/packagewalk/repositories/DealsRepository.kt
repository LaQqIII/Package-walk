package com.example.packagewalk.repositories

import android.util.Log
import com.example.packagewalk.data.Deal
import com.example.packagewalk.data.MyResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DealsRepository @Inject constructor() {

    suspend fun issueDeals(from: String, to: String, data: String): MyResult<List<Deal>> {
        delay(2000)
        return MyResult.Success(
            listOf(
                Deal(from, to, data, 0),
                Deal(from, to, data, 0),
                Deal(from, to, data, 0),
                Deal(from, to, data, 0)
            )
        )
    }

    suspend fun issueDealsUser(): MyResult<List<Deal>> {
        delay(2000)
        return MyResult.Success(
            listOf(
                Deal("Саров", "Нижний Новгород", "12.12", 0),
                Deal("Саров", "Нижний Новгород", "12.12", 0),
                Deal("Саров", "Нижний Новгород", "12.12", 0),
                Deal("Саров", "Нижний Новгород", "12.12", 0)
            )
        )
    }

    suspend fun createNewDeal(deal: Deal): MyResult<Boolean> {
        return try {
            FirebaseFirestore
                .getInstance()
                .collection("deals")
                .document()
                .set(deal)
                .await()
            MyResult.Success(true)
        } catch (e: Exception) {
            Log.d("!@#", "ошибка при создании нового сделки=$e")
            MyResult.Error(e)
        }
    }

}