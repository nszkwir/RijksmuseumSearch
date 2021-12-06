package com.nszkwir.rijksmuseumsearch.data.art_objects.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinksDto(
    @SerializedName("self") val self: String? = "",
    @SerializedName("web") val web: String? = ""
) : Parcelable
