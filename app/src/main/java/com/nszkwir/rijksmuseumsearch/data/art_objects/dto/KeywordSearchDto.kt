package com.nszkwir.rijksmuseumsearch.data.art_objects.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class KeywordSearchDto(
    @SerializedName("count") val count: Int,
    @SerializedName("artObjects") val artObjects: List<ArtObjectDto>? = listOf(),
) : Parcelable
