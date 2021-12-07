package com.nszkwir.rijksmuseumsearch.domain.use_cases

import com.nszkwir.rijksmuseumsearch.domain.models.ArtObject
import com.nszkwir.rijksmuseumsearch.domain.result_data.ArtObjectKeywordSearchResult

interface ArtObjectKeywordSearchUseCase {
    suspend fun artObjectKeywordSearch(query: String):
            ArtObjectKeywordSearchResult<ArrayList<ArtObject>>
}