package com.nszkwir.rijksmuseumsearch.presentation.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nszkwir.rijksmuseumsearch.common.utils.Event
import com.nszkwir.rijksmuseumsearch.common.utils.NavigationCommand

abstract class BaseViewModel : ViewModel() {

    protected val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    protected val _snackbarError = MutableLiveData<Event<String>>()
    val snackbarError: LiveData<Event<String>> = _snackbarError

    protected val _loading = MutableLiveData<Event<Boolean>>()
    val loading: LiveData<Event<Boolean>> = _loading

}
