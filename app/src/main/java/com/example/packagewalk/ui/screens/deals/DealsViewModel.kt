package com.example.packagewalk.ui.screens.deals

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.Deal
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.repositories.DealsRepository
import com.example.packagewalk.ui.screens.deals.models.DealsEventState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealsViewModel
@Inject constructor(private val repository: DealsRepository) : ViewModel() {

    val dealsEvent = MutableLiveData(CREATE)

    val deals = mutableStateListOf<Deal>()

    fun loadingDeals() {
        viewModelScope.launch(Dispatchers.IO) {
            dealsEvent.postValue(LOADING)
            when (val result = repository.issueDealsUser()) {
                is MyResult.Success -> {
                    if (result.data.isEmpty()) {
                        dealsEvent.postValue(EMPTY)
                    } else {
                        dealsEvent.postValue(LOADED)
                        deals.clear()
                        deals.addAll(result.data)
                    }
                }
                is MyResult.Error -> dealsEvent.postValue(ERROR)
            }
        }
    }
}