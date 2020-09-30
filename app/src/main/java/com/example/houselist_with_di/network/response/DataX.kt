package com.example.houselist_with_di.network.response

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class DataX(
    var address: String?,
    @Embedded(prefix = "cover_")
    var cover: Cover,
    var description: String?,
    var id: Int?,
//    var media: Any,
//    var owner: Owner,
    var price: Int?,
//    var `protected`: Int,
    var seo_desc: String?,
    var seo_title: String?,
//    var slug: String,
    var title: String?,
) : Parcelable