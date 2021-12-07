package com.nszkwir.rijksmuseumsearch.domain

import com.nszkwir.rijksmuseumsearch.data.art_objects.repository.ArtObjectRepository
import com.nszkwir.rijksmuseumsearch.data.art_objects.result_data.ArtObjectRepositoryResult
import com.nszkwir.rijksmuseumsearch.domain.converters.toArrayOfArtObjects
import com.nszkwir.rijksmuseumsearch.domain.models.ArtObject
import com.nszkwir.rijksmuseumsearch.domain.result_data.ArtObjectKeywordSearchResult
import com.nszkwir.rijksmuseumsearch.domain.use_cases.ArtObjectKeywordSearchUseCase
import javax.inject.Inject

class MainUseCasesImpl @Inject constructor(
    private val repository: ArtObjectRepository
) : ArtObjectKeywordSearchUseCase {
    override suspend fun artObjectKeywordSearch(query: String)
            : ArtObjectKeywordSearchResult<ArrayList<ArtObject>> {
        val result = repository.keywordSearch(query = query)
        return when (result) {
            is ArtObjectRepositoryResult.SuccessFromNetwork -> {
                ArtObjectKeywordSearchResult.Success(
                    result.data!!.toArrayOfArtObjects()
                )
            }
            is ArtObjectRepositoryResult.Error -> {
                if (result.isNetworkError) {
                    ArtObjectKeywordSearchResult.NetworkError()
                } else {
                    ArtObjectKeywordSearchResult.Error()
                }
            }
        }
    }
}
