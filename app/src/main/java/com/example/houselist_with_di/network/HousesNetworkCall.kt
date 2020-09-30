package com.example.houselist_with_di.network

import com.example.houselist_with_di.network.response.Data
import com.example.houselist_with_di.network.response.DataX
import retrofit2.http.GET

interface HousesNetworkCall {
    @GET ("api/home")
    suspend fun getHouses(): List<DataX>
}