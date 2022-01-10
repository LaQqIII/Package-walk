package com.example.packagewalk.ui.screens.deal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.User
import com.example.packagewalk.data.documents.Deal
import com.example.packagewalk.repositories.DealsRepositoryForCustomers
import com.example.packagewalk.ui.screens.deal.models.DealEventState
import com.example.packagewalk.ui.screens.deal.models.DealEventState.CANCEL_DEAL
import com.example.packagewalk.ui.screens.deal.models.DealEventState.ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealViewModel
@Inject constructor(private val repository: DealsRepositoryForCustomers) : ViewModel() {

    val state = mutableStateOf<DealEventState?>(null)

    val loading = mutableStateOf(false)

    /** Отменяет сделку. Может вызвать пользователь для своей открытой сделки */
    fun cancelDeal(deal: Deal) {
        if (deal.customerPhoneNumber != User.phoneNumber) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            when (val result = repository.cancelDeal(deal)) {
                is MyResult.Success -> {
                    if (result.data) {
                        state.value = CANCEL_DEAL
                    } else {
                        state.value = ERROR
                    }
                }
                is MyResult.Error -> state.value = ERROR
            }
            loading.value = false
        }
    }

    /** Закрывает сделку. Может вызвать пользователь для своей открытой сделки */
    fun closeDeal(deal: Deal) {
        if (deal.customerPhoneNumber != User.phoneNumber) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            when (val result = repository.closeDeal(deal)) {
                is MyResult.Success -> {
                    if (result.data) {
                        state.value = CANCEL_DEAL
                    } else {
                        state.value = ERROR
                    }
                }
                is MyResult.Error -> state.value = ERROR
            }
            loading.value = false
        }
    }
}