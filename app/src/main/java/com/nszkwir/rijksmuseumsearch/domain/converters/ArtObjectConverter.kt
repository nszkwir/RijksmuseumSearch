package com.nszkwir.rijksmuseumsearch.domain.converters

import com.nszkwir.rijksmuseumsearch.data.art_objects.dto.ArtObjectDto
import com.nszkwir.rijksmuseumsearch.data.art_objects.dto.KeywordSearchDto
import com.nszkwir.rijksmuseumsearch.domain.models.ArtObject

// TODO this may be implemented as an Object converter instead extension functions
internal fun ArtObjectDto.toArtObject(): ArtObject {
    return ArtObject(
        id = this.id,
        objectNumber = this.objectNumber,
        title = this.title,
        longTitle = this.longTitle,
        principalOrFirstMaker = this.principalOrFirstMaker,
        webLink = this.links.web ?: "",
        hasImage = this.hasImage,
        webImageLink = this.webImage.url ?: "",
        headerImageLink = this.headerImage.url ?: ""
    )
}

internal fun KeywordSearchDto.toArrayOfArtObjects(): ArrayList<ArtObject> {
    return this.artObjects?.map {
        it.toArtObject()
    } as ArrayList<ArtObject>
}