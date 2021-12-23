package com.example.packagewalk.ui.screens.new_deal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.Deal
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.PackageSize
import com.example.packagewalk.repositories.DealsRepository
import com.example.packagewalk.ui.screens.new_deal.models.NewDealEventState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewDealViewModel
@Inject constructor(private val repository: DealsRepository) : ViewModel() {

    val newDealEvent = MutableLiveData(START)

    val from = mutableStateOf("")
    val to = mutableStateOf("")
    val data = mutableStateOf("")
    val size = mutableStateOf(PackageSize.SMALL)
    val startCheck = mutableStateOf(false)
    val loading = mutableStateOf(false)

    fun createNewDeal() {
        startCheck.value = true
        if (checkInput()) return
        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            when (val result = repository.createNewDeal(
                Deal(
                    from = from.value,
                    to = to.value,
                    data = data.value,
                    size = size.value.id
                )
            )) {
                is MyResult.Success -> {
                    if (result.data) {
                        newDealEvent.postValue(CREATE)
                    } else {
                        newDealEvent.postValue(NOT_CREATED)
                    }
                }
                is MyResult.Error -> newDealEvent.postValue(ERROR)
            }
            loading.value = false
        }
    }

    private fun checkInput(): Boolean =
        from.value.isEmpty() || to.value.isEmpty() || data.value.isEmpty()
}