package com.example.houselist_with_di.network.response

import com.example.houselist_with_di.network.response.Data
import com.example.houselist_with_di.network.response.Debug

data class HousesResponse(
    var code: String,
    var `data`: Data,
    var debug: Debug,
    var message: String
)