package com.example.packagewalk.ui.screens.new_deal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.Deal
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.PackageSize
import com.example.packagewalk.data.User
import com.example.packagewalk.repositories.DealsRepository
import com.example.packagewalk.ui.screens.new_deal.models.NewDealEventState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewDealViewModel
@Inject constructor(private val repository: DealsRepository) : ViewModel() {

    val newDealEvent = mutableStateOf(START)

    val from = mutableStateOf("")
    val to = mutableStateOf("")
    val data = mutableStateOf("")
    val size = mutableStateOf(PackageSize.SMALL)
    val startCheck = mutableStateOf(false)
    val loading = mutableStateOf(false)

    fun createNewDeal() {
        startCheck.value = true
        if (checkInput()) return
        if (User.phoneNumber == null) {
            newDealEvent.value = ERROR
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            when (val result = repository.createNewDeal(
                Deal(
                    from = from.value,
                    to = to.value,
                    data = data.value,
                    size = size.value.id,
                    phoneNumber = User.phoneNumber!!
                )
            )) {
                is MyResult.Success -> {
                    if (result.data) {
                        newDealEvent.value = CREATE
                    } else {
                        newDealEvent.value = NOT_CREATED
                    }
                }
                is MyResult.Error -> newDealEvent.value = ERROR
            }
            loading.value = false
        }
    }

    private fun checkInput(): Boolean =
        from.value.isEmpty() || to.value.isEmpty() || data.value.isEmpty()
}