package com.example.packagewalk.ui.screens.new_deal

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.User
import com.example.packagewalk.data.documents.Deal
import com.example.packagewalk.data.enums.DealStatus
import com.example.packagewalk.data.enums.PackageSize
import com.example.packagewalk.repositories.DealsRepositoryForCustomers
import com.example.packagewalk.repositories.PlacesRepository
import com.example.packagewalk.ui.screens.new_deal.models.NewDealEventState
import com.example.packagewalk.ui.screens.new_deal.models.NewDealEventState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewDealViewModel
@Inject constructor(
    private val repository: DealsRepositoryForCustomers,
    private val placesRepository: PlacesRepository
) : ViewModel() {

    val newDealEvent = mutableStateOf<NewDealEventState?>(null)

    val from = mutableStateOf("")
    val to = mutableStateOf("")
    val data = mutableStateOf("")
    val size = mutableStateOf(PackageSize.SMALL)
    val cost = mutableStateOf(0)
    val startCheck = mutableStateOf(false)
    val loading = mutableStateOf(false)
    val cities = mutableStateListOf<String>()

    fun createNewDeal() {
        startCheck.value = true
        if (checkInput()) return
        if (User.phoneNumber == null) {
            newDealEvent.value = ERROR
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            loading.value = true
            val date = prepareDate(data.value)
            when (val result = repository.createNewDeal(
                Deal(
                    status = DealStatus.OPEN,
                    from = from.value,
                    to = to.value,
                    data = date,
                    size = size.value.id,
                    cost = cost.value,
                    customer = User.id ?: "",
                    customerName = User.name ?: "",
                    customerPhoneNumber = User.phoneNumber!!
                )
            )) {
                is MyResult.Success -> {
                    if (result.data) {
                        newDealEvent.value = CREATED
                    } else {
                        newDealEvent.value = NOT_CREATED
                    }
                }
                is MyResult.Error -> newDealEvent.value = ERROR
            }
            loading.value = false
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
                is MyResult.Error -> {}
            }
        }
    }

    private fun checkInput(): Boolean =
        from.value.isEmpty() || to.value.isEmpty() || data.value.isEmpty()

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