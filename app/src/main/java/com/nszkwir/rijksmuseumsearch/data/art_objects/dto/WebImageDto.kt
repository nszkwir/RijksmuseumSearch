package com.nszkwir.rijksmuseumsearch.data.art_objects.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WebImageDto(
    @SerializedName("width") val width: Int? = 0,
    @SerializedName("height") val height: Int? = 0,
    @SerializedName("url") val url: String? = ""
) : Parcelable
