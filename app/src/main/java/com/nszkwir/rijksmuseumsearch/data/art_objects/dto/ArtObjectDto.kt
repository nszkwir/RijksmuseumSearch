package com.nszkwir.rijksmuseumsearch.data.art_objects.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtObjectDto(
    @SerializedName("id") val id: String?,
    @SerializedName("objectNumber") val objectNumber: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("longTitle") val longTitle: String?,
    @SerializedName("principalOrFirstMaker") val principalOrFirstMaker: String?,
    @SerializedName("links") val links: LinksDto?,
    @SerializedName("hasImage") val hasImage: Boolean?,
    @SerializedName("webImage") val webImage: WebImageDto?,
    @SerializedName("headerImage") val headerImage: WebImageDto?,
) : Parcelable
