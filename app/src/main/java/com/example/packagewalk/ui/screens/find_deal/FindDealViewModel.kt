package com.example.packagewalk.ui.screens.find_deal

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.documents.Deal
import com.example.packagewalk.repositories.DealsRepositoryForExecutor
import com.example.packagewalk.repositories.PlacesRepository
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindDealViewModel
@Inject constructor(
    private val repository: DealsRepositoryForExecutor,
    private val placesRepository: PlacesRepository
) : ViewModel() {

    val findDealEvent = mutableStateOf(FILTER)

    val from = mutableStateOf("")
    val to = mutableStateOf("")
    val data = mutableStateOf("")
    val deals = mutableStateListOf<Deal>()
    val cities = mutableStateListOf<String>()
    val startCheck = mutableStateOf(false)
    val loading = mutableStateOf(false)

    fun loadingDeals() {
        startCheck.value = true
        if (from.value.isEmpty() || to.value.isEmpty() || data.value.isEmpty())
            return
        viewModelScope.launch(Dispatchers.IO) {
            findDealEvent.value = SEARCH
            loading.value = true
            val date = prepareDate(data.value)
            when (val result = repository.issueDeals(
                from = from.value,
                to = to.value,
                data = date
            )) {
                is MyResult.Success -> {
                    loading.value = false
                    deals.clear()
                    deals.addAll(result.data)
                }
                is MyResult.Error -> findDealEvent.value = ERROR
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun loadingCities(query: String) {
        if (query.length <= 3)
            return
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = placesRepository.issueCities(query)) {
                is MyResult.Success -> {
                    cities.clear()
                    cities.addAll(result.data)
                }
                is MyResult.Error -> cities.clear()
            }
        }
    }

    private fun prepareDate(inputText: String): String {
        var out = ""
        inputText.forEachIndexed { index, char ->
            when (index) {
                2 -> out += "/$char"
                4 -> out += "/$char"
                else -> out += char
            }
        }
        return out
    }
}