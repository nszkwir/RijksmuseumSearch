package com.nszkwir.rijksmuseumsearch.data.art_objects.repository

import com.nszkwir.rijksmuseumsearch.common.internal_functions.safeCall
import com.nszkwir.rijksmuseumsearch.common.network.ResultData
import com.nszkwir.rijksmuseumsearch.data.art_objects.dto.KeywordSearchDto
import com.nszkwir.rijksmuseumsearch.data.art_objects.result_data.ArtObjectRepositoryResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArtObjectRepositoryImpl @Inject constructor(
    private val service: ArtObjectService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

) : ArtObjectRepository {
    override suspend fun keywordSearch(
        p: Int,
        ps: Int,
        query: String
    ): ArtObjectRepositoryResult<KeywordSearchDto?> {
        return withContext(dispatcher) {
            val result = safeCall {
                service.keywordSearch(
                    p = 0,
                    ps = 1,
                    q = query
                )
            }
            when (result) {
                is ResultData.Success -> {
                    return@withContext ArtObjectRepositoryResult.SuccessFromNetwork(result.data!!)
                }
                else ->
                    return@withContext ArtObjectRepositoryResult.Error(
                        result.isNetworkError(), result.getErrorCode()
                    )
            }
        }
    }
}