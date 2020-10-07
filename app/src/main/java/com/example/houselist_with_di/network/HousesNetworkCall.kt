package com.example.houselist_with_di.network

import com.example.houselist_with_di.network.response.HousesResponse
import dagger.hilt.android.components.ActivityComponent
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface HousesNetworkCall {
    @GET ("api/home")
    suspend fun getHouses(@Query("page") page: String): HousesResponse
}