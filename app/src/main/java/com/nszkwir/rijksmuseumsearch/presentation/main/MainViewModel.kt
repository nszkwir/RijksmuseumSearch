package com.nszkwir.rijksmuseumsearch.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nszkwir.rijksmuseumsearch.common.utils.Event
import com.nszkwir.rijksmuseumsearch.domain.MainUseCasesImpl
import com.nszkwir.rijksmuseumsearch.domain.models.ArtObject
import com.nszkwir.rijksmuseumsearch.domain.result_data.ArtObjectKeywordSearchResult
import com.nszkwir.rijksmuseumsearch.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCases: MainUseCasesImpl
) : BaseViewModel() {

    // TODO implement a MainViewState class to observe from fragment instead observig data
    private val _artObjects = MutableLiveData<Event<ArrayList<ArtObject>>>()
    val artObjects: LiveData<Event<ArrayList<ArtObject>>> = _artObjects

    private val _searchError = MutableLiveData<Event<String>>()
    val searchError: LiveData<Event<String>> = _searchError

    fun processViewEvent(viewEvent: MainViewEvent) {
        when (viewEvent) {
            is MainViewEvent.ErrorMessage -> {
                _snackbarError.value = Event(viewEvent.message)
            }
            is MainViewEvent.KeywordSearch -> {
                doKeywordSearch(viewEvent.query)
            }
            is MainViewEvent.LoadingView -> {
                _loading.value = Event(viewEvent.isLoading)
            }
        }
    }

    private fun doKeywordSearch(query: String) {
        viewModelScope.launch {
            val searchResult = mainUseCases.artObjectKeywordSearch(query)
            when (searchResult) {
                is ArtObjectKeywordSearchResult.Success -> {
                    _artObjects.value = Event(searchResult.data)
                }
                is ArtObjectKeywordSearchResult.Error -> {
                    _searchError.value = Event("Error")
                }
                is ArtObjectKeywordSearchResult.NetworkError -> {
                    _searchError.value = Event("Network Error")
                }
            }
        }
    }

}