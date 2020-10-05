package com.example.houselist_with_di.network

import com.example.houselist_with_di.network.response.Cover
import com.example.houselist_with_di.network.response.Data
import com.example.houselist_with_di.network.response.DataX
import com.example.houselist_with_di.network.response.HousesResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.http.GET
import javax.inject.Inject

interface HousesNetworkCall {
    @GET ("api/home")
    suspend fun getHouses(): HousesResponse
}