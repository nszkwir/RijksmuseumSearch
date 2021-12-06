package com.nszkwir.rijksmuseumsearch.presentation.main

import com.nszkwir.rijksmuseumsearch.domain.MainUseCases
import com.nszkwir.rijksmuseumsearch.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCases: MainUseCases
) : BaseViewModel() {
    // TODO: Implement the ViewModel
}