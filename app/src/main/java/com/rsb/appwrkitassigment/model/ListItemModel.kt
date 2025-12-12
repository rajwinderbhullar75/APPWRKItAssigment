package com.rsb.appwrkitassigment.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListItemModel(
    var id: Int,
    var title: String,
    var description: String,
    var status: String
) : Parcelable

