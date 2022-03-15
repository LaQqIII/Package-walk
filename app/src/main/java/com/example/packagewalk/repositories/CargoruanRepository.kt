package com.example.packagewalk.repositories

import android.util.Log
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.references.Сargoruan
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CargoruanRepository @Inject constructor() {

    companion object {
        private const val CARGORUANS = "cargoruans"
        private const val PHONE_NUMBER = "phoneNumber"
    }

    private val firebaseFirestore = FirebaseFirestore.getInstance()

    private fun cargoruansCollection() = firebaseFirestore.collection(CARGORUANS)

    suspend fun addSupporters(cargoruan: Сargoruan): MyResult<Boolean> {
        return try {
            cargoruansCollection()
                .document()
                .set(cargoruan)
                .await()
            MyResult.Success(true)
        } catch (e: Exception) {
            Log.e("!@#", "ошибка при добавлении нового каргоруанца=$e")
            MyResult.Error(e)
        }
    }

    suspend fun issueCargoruan(phoneNumber: String): MyResult<Сargoruan?> {
        return try {
            val result = cargoruansCollection()
                .whereEqualTo(PHONE_NUMBER, phoneNumber)
                .get()
                .await()
                .map { it.toObject(Сargoruan::class.java) }
                .firstOrNull()
            MyResult.Success(result)
        } catch (e: Exception) {
            Log.e("!@#", "ошибка при получении данных об каргоруанце=$e")
            MyResult.Error(e)
        }
    }

}