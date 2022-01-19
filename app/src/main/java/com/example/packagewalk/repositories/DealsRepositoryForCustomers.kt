package com.example.packagewalk.repositories

import android.util.Log
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.documents.Deal
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/** Репозиторий для работы со сделками для заказчика.
 * Используется для создания, отмены и закрытия сделок заказчика.
 */
class DealsRepositoryForCustomers @Inject constructor() {

    companion object {
        private const val OPEN_DEALS = "open_deals"
        private const val CLOSE_DEALS = "close_deals"
        private const val CANCEL_DEALS = "cancel_deals"
        private const val PHONE_FIELD = "phoneNumber"
    }

    private val firebaseFirestore = FirebaseFirestore.getInstance()

    private fun openDealCollection() = firebaseFirestore.collection(OPEN_DEALS)

    private fun closeDealCollection() = firebaseFirestore.collection(CLOSE_DEALS)

    private fun cancelDealCollection() = firebaseFirestore.collection(CANCEL_DEALS)

    /** Создает новую запись в бд */
    suspend fun createNewDeal(deal: Deal): MyResult<Boolean> {
        return try {
            openDealCollection()
                .document()
                .set(deal)
                .await()
            MyResult.Success(true)
        } catch (e: Exception) {
            Log.e("!@#", "ошибка при создании новой сделки=$e")
            MyResult.Error(e)
        }
    }

    /** Удаляет сделку из коллекции открытых сделок
     * и делает новую запись в коллекции отмененные сделки
     */
    suspend fun cancelDeal(deal: Deal): MyResult<Boolean> {
        return try {
            cancelDealCollection()
                .document()
                .set(deal)
                .await()
            openDealCollection()
                .document(deal.id)
                .delete()
                .await()
            MyResult.Success(true)
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }

    /** Удаляет сделку из коллекции открытых сделок
     * и делает новую запись в коллекции закрытые сделки
     */
    suspend fun closeDeal(deal: Deal): MyResult<Boolean> {
        return try {
            closeDealCollection()
                .document()
                .set(deal)
                .await()
            openDealCollection()
                .document(deal.id)
                .delete()
                .await()
            Log.v("!@#", "Сделка успешно закрыта")
            MyResult.Success(true)
        } catch (e: Exception) {
            Log.e("!@#", "Возникли ошибки при закрытии сделки=$e")
            MyResult.Error(e)
        }
    }
}