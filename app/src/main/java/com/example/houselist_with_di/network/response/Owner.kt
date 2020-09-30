package com.example.houselist_with_di.network.response

import com.example.houselist_with_di.network.response.Avatar

data class Owner(
    var address: String,
    var agencyTitle: String,
    var agency_id: Int,
    var avatar: Avatar,
    var city_id: Int,
    var description: String,
    var id: Int,
    var lat: Double,
    var link: String,
    var lng: Double,
    var order: Any,
    var postcode: String,
    var resourceType: String,
    var single: Int,
    var slug: String,
    var status: String,
    var title: String,
    var type: String
)