package com.example.packagewalk.ui.screens.find_deal

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.Deal
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.repositories.DealsRepository
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindDealViewModel
@Inject constructor(private val repository: DealsRepository) : ViewModel() {

    val findDealEvent = MutableLiveData(CREATE)

    val from = mutableStateOf("")
    val to = mutableStateOf("")
    val data = mutableStateOf("")
    val deals = mutableStateListOf<Deal>()

    fun loadingDeals() {
        if (from.value.isEmpty() || to.value.isEmpty() || data.value.isEmpty())
            return
        viewModelScope.launch(Dispatchers.IO) {
            findDealEvent.postValue(LOADING)
            when (val result = repository.issueDeals(
                from = from.value,
                to = to.value,
                data = data.value
            )) {
                is MyResult.Success -> {
                    if (result.data.isEmpty()) {
                        findDealEvent.postValue(EMPTY)
                    } else {
                        findDealEvent.postValue(LOADED)
                        deals.clear()
                        deals.addAll(result.data)
                    }
                }
                is MyResult.Error -> findDealEvent.postValue(ERROR)
            }
        }
    }
}