package com.example.packagewalk.repositories

import android.util.Log
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.documents.Deal
import com.example.packagewalk.extensions.toCloseDeal
import com.example.packagewalk.extensions.toOpenDeal
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/** Базовый репозиторий для работы со сделками.
 * Используется для просмотра активности пользователя.
 */
class DealsRepository @Inject constructor() {

    companion object {
        private const val OPEN_DEALS = "open_deals"
        private const val CLOSE_DEALS = "close_deals"
        private const val PHONE_FIELD = "phoneNumber"
    }

    private val firebaseFirestore = FirebaseFirestore.getInstance()

    private fun openDealCollection() = firebaseFirestore.collection(OPEN_DEALS)

    private fun closeDealCollection() = firebaseFirestore.collection(CLOSE_DEALS)

    /** Возвращает активность конкретного пользователя (открытые и закрытые сделки)
     * @param phoneNumber - номер телефона пользователя
     */
    suspend fun issueDealsUser(phoneNumber: String): MyResult<List<Deal>> {
        return try {
            val openDeals = openDealCollection()
                .whereEqualTo(PHONE_FIELD, phoneNumber)
                .get()
                .await()
                .map { it.toOpenDeal() }
            val closeDeals = closeDealCollection()
                .whereEqualTo(PHONE_FIELD, phoneNumber)
                .get()
                .await()
                .map { it.toCloseDeal() }
            Log.d("!@#", "Данные об активности пользователя получены успешно")
            MyResult.Success(openDeals + closeDeals)
        } catch (e: Exception) {
            Log.e("!@#", "Возникли ошибки при получении данных о активности пользователя")
            MyResult.Error(e)
        }
    }
}