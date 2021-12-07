package com.nszkwir.rijksmuseumsearch.presentation.main

sealed class MainViewEvent {

    data class LoadingView(
        val isLoading: Boolean
    ) : MainViewEvent()

    data class ErrorMessage(
        val message: String
    ) : MainViewEvent()

    data class KeywordSearch(
        val query: String
    ) : MainViewEvent()

    // TODO may add navigation events
    //data class goToArtObjectDetails(val artObjectId: Int) : MainViewEvent()
}