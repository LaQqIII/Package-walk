package com.example.packagewalk.repositories

import android.content.Context
import android.graphics.Typeface
import android.text.style.StyleSpan
import android.util.Log
import com.example.packagewalk.data.MyResult
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.ktx.api.net.awaitFindAutocompletePredictions
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class PlacesRepository @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        private const val API_KEY = "AIzaSyBvVaT_capZoAcY4aAUadzKo6WpKGGVHao"
        private val STYLE_BOLD = StyleSpan(Typeface.BOLD)
        private const val COUNT_CITIES = 10
    }

    private val placeClient: PlacesClient

    init {
        if (!Places.isInitialized()) {
            Places.initialize(context, API_KEY)
        }
        placeClient = Places.createClient(context)
    }

    /** Возвращает список городов, которые соответствуют переданной строке
     * !Возвращает только COUNT_CITIES вариантов! */
    @ExperimentalCoroutinesApi
    suspend fun issueCities(query: String): MyResult<List<String>> {
        return try {
            val response = placeClient.awaitFindAutocompletePredictions {
                typeFilter = TypeFilter.CITIES
                this.query = query
                countries = listOf("RU")
            }
            Log.v("!@#", "Список городов успешно получен=$response")
            MyResult.Success(response.autocompletePredictions.take(COUNT_CITIES).map {
                it.getPrimaryText(STYLE_BOLD).toString()
            })
        } catch (e: Exception) {
            Log.e("!@#", "Возникли ошибки при получении списка городов=$e")
            MyResult.Error(e)
        }
    }
}