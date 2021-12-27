package com.example.packagewalk.ui.screens.deals

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.Deal
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.User
import com.example.packagewalk.repositories.DealsRepository
import com.example.packagewalk.ui.screens.deals.models.DealsEventState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealsViewModel
@Inject constructor(private val repository: DealsRepository) : ViewModel() {

    val dealsEvent = mutableStateOf(CREATE)

    val deals = mutableStateListOf<Deal>()

    fun loadingDeals() {
        if (User.phoneNumber == null) {
            dealsEvent.value = ERROR
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            dealsEvent.value = LOADING
            when (val result = repository.issueDealsUser(User.phoneNumber!!)) {
                is MyResult.Success -> {
                    if (result.data.isEmpty()) {
                        dealsEvent.value = EMPTY
                    } else {
                        dealsEvent.value = LOADED
                        deals.clear()
                        deals.addAll(result.data)
                    }
                }
                is MyResult.Error -> dealsEvent.value = ERROR
            }
        }
    }
}