package com.nszkwir.rijksmuseumsearch.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtObject(
    val id: String,
    val objectNumber: String,
    val title: String,
    val longTitle: String,
    val principalOrFirstMaker: String,
    val webLink: String,
    val hasImage: Boolean,
    val webImageLink: String?,
    val headerImageLink: String?,
) : Parcelable