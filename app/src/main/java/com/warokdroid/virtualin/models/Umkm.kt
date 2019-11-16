package com.warokdroid.virtualin.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Umkm(
    var name: String? = null,
    var founder: String? = null,
    var rating: Double? = null,
    var icon: Int? = null,
    var about: String? = null
) : Parcelable