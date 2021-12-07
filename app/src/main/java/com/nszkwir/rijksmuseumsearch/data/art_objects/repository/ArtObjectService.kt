package com.nszkwir.rijksmuseumsearch.data.art_objects.repository

import com.nszkwir.rijksmuseumsearch.common.network.KEY
import com.nszkwir.rijksmuseumsearch.data.art_objects.dto.KeywordSearchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtObjectService {
    @GET("collection")
    suspend fun keywordSearch(
        @Query("key") key: String = KEY,
        @Query("p") p: Int,
        @Query("ps") ps: Int,
        @Query("q") q: String
    ): Response<KeywordSearchDto>
}
