package com.nszkwir.rijksmuseumsearch.data.art_objects.repository

import com.nszkwir.rijksmuseumsearch.data.art_objects.dto.KeywordSearchDto
import com.nszkwir.rijksmuseumsearch.data.art_objects.result_data.ArtObjectRepositoryResult

interface ArtObjectRepository {
    suspend fun keywordSearch(
        p: Int,
        ps: Int,
        query: String
    ): ArtObjectRepositoryResult<KeywordSearchDto?>
}
