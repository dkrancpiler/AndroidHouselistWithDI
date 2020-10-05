package com.example.houselist_with_di.models

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

data class House (
    var id: Int?,
    var title: String?,
    var title_short: String?,
    var image: String,
    var description: String?,
    var description_short: String?,
    var price: Int?,
    var address: String?

){
}