package com.example.packagewalk.repositories

import com.example.packagewalk.data.Deal
import com.example.packagewalk.data.MyResult
import kotlinx.coroutines.delay
import javax.inject.Inject

class DealsRepository @Inject constructor() {
    suspend fun issueDeals(from: String, to: String, data: String): MyResult<List<Deal>> {
        delay(2000)
        return MyResult.Success(listOf(Deal(from, to, data)))
    }
}