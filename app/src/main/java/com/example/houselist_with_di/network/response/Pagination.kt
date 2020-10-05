package com.example.houselist_with_di.network.response

import javax.inject.Inject

data class Pagination (
    var lastPage: Int?,
    var page: Int?,
    var perPage: Int?,
//    var total: Int
)