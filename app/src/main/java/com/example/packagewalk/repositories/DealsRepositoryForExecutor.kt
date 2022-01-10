package com.example.packagewalk.repositories

import android.util.Log
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.documents.Deal
import com.example.packagewalk.extensions.toOpenDeal
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/** Репозиторий для работы со сделками для исполнителя.
 * Используется для поиска подходящих сделок для исполнителя.
 */
class DealsRepositoryForExecutor @Inject constructor() {

    companion object {
        private const val OPEN_DEALS = "open_deals"
        private const val FROM_FIELD = "from"
        private const val TO_FIELD = "to"
        private const val DATA_FIELD = "data"
    }

    private val firebaseFirestore = FirebaseFirestore.getInstance()

    private fun openDealCollection() = firebaseFirestore.collection(OPEN_DEALS)

    /** Возвращает список открытых сделок, которые соответствуют переданным аргументом */
    suspend fun issueDeals(from: String, to: String, data: String): MyResult<List<Deal>> {
        return try {
            Log.v("!@#", "Попытка найти сделки от $from до $to на $data")
            val deals = openDealCollection()
                .whereEqualTo(DATA_FIELD, data)
                .whereEqualTo(FROM_FIELD, from)
                .whereEqualTo(TO_FIELD, to)
                .get()
                .await()
                .map { it.toOpenDeal() }
            MyResult.Success(deals)
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }
}