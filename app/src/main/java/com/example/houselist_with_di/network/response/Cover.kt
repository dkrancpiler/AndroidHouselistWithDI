package com.example.houselist_with_di.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Cover(
    val background: String,
    val description: @RawValue Any,
    val id: @RawValue Any,
    val title: String,
    val type: String
) : Parcelable