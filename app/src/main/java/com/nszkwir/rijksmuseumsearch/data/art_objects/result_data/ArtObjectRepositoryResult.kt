package com.nszkwir.rijksmuseumsearch.data.art_objects.result_data

sealed class ArtObjectRepositoryResult<out T> {
    data class SuccessFromNetwork<out T>(val data: T) :
        ArtObjectRepositoryResult<T>()

    data class Error(val isNetworkError: Boolean, val code: Int = DEFAULT_ERROR_CODE) :
        ArtObjectRepositoryResult<Nothing>()

    companion object {
        const val DEFAULT_ERROR_CODE = -999
    }
}
