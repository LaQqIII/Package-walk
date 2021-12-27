package com.example.packagewalk.repositories

import android.util.Log
import com.example.packagewalk.data.Deal
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.extensions.toCloseDeal
import com.example.packagewalk.extensions.toOpenDeal
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DealsRepository @Inject constructor() {

    companion object {
        private const val OPEN_DEALS = "open_deals"
        private const val CLOSE_DEALS = "close_deals"
        private const val CANCEL_DEALS = "cancel_deals"
    }

    /** Возвращает список открытых сделок, которые соответствуют переданным аргументом */
    suspend fun issueDeals(from: String, to: String, data: String): MyResult<List<Deal.OpenDeal>> {
        return try {
            val deals = FirebaseFirestore
                .getInstance()
                .collection(OPEN_DEALS)
                .whereEqualTo("data", data)
                .whereEqualTo("from", from)
                .whereEqualTo("to", to)
                .get()
                .await()
                .map { it.toOpenDeal() }
            MyResult.Success(deals)
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }

    /** Возвращает активность конкретного пользователя (открытые и закрытые сделки)
     * @param phoneNumber - номер телефона пользователя
     */
    suspend fun issueDealsUser(phoneNumber: String): MyResult<List<Deal>> {
        return try {
            val openDeals = FirebaseFirestore
                .getInstance()
                .collection(OPEN_DEALS)
                .whereEqualTo("phoneNumber", phoneNumber)
                .get()
                .await()
                .map { it.toOpenDeal() }
            val closeDeals = FirebaseFirestore
                .getInstance()
                .collection(CLOSE_DEALS)
                .whereEqualTo("phoneNumber", phoneNumber)
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

    /** Создает новую запись в бд */
    suspend fun createNewDeal(deal: Deal.OpenDeal): MyResult<Boolean> {
        return try {
            FirebaseFirestore
                .getInstance()
                .collection(OPEN_DEALS)
                .document()
                .set(deal)
                .await()
            MyResult.Success(true)
        } catch (e: Exception) {
            Log.d("!@#", "ошибка при создании новой сделки=$e")
            MyResult.Error(e)
        }
    }

    /** Удаляет сделку из коллекции открытых сделок
     * и делает новую запись в коллекции отмененные сделки
     */
    suspend fun cancelDeal(deal: Deal.OpenDeal): MyResult<Boolean> {
        return try {
            FirebaseFirestore
                .getInstance()
                .collection(CANCEL_DEALS)
                .document()
                .set(deal)
                .await()
            FirebaseFirestore
                .getInstance()
                .collection(OPEN_DEALS)
                .document(deal.id)
                .delete()
                .await()
            MyResult.Success(true)
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }
}