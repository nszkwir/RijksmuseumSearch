package com.nszkwir.rijksmuseumsearch.domain.result_data

sealed class ArtObjectKeywordSearchResult<out T> {
    data class Success<out T>(val data: T) :
        ArtObjectKeywordSearchResult<T>()

    data class NetworkError(val code: Int = DEFAULT_NETWORK_ERROR_CODE) :
        ArtObjectKeywordSearchResult<Nothing>()

    data class Error(val code: Int = DEFAULT_ERROR_CODE) :
        ArtObjectKeywordSearchResult<Nothing>()

    companion object {
        const val DEFAULT_NETWORK_ERROR_CODE = -999
        const val DEFAULT_ERROR_CODE = -999
    }
}
