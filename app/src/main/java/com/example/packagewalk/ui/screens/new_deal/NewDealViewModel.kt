package com.example.packagewalk.ui.screens.new_deal

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packagewalk.data.Deal.OpenDeal
import com.example.packagewalk.data.MyResult
import com.example.packagewalk.data.PackageSize
import com.example.packagewalk.data.User
import com.example.packagewalk.repositories.DealsRepository
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
    private val repository: DealsRepository,
    private val placesRepository: PlacesRepository
) : ViewModel() {

    val newDealEvent = mutableStateOf<NewDealEventState?>(null)

    val from = mutableStateOf("")
    val to = mutableStateOf("")
    val data = mutableStateOf("")
    val size = mutableStateOf(PackageSize.SMALL)
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
            when (val result = repository.createNewDeal(
                OpenDeal(
                    from = from.value,
                    to = to.value,
                    data = data.value,
                    size = size.value.id,
                    phoneNumber = User.phoneNumber!!
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
}