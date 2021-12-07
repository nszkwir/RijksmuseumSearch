package com.nszkwir.rijksmuseumsearch.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.nszkwir.rijksmuseumsearch.R
import com.nszkwir.rijksmuseumsearch.common.utils.Event

fun Fragment.showSnackbar(text: String, duration: Int) {
    activity?.let {
        val snackbar = Snackbar.make(
            it.findViewById(R.id.container),
            text,
            duration
        )
        val view = snackbar.view
        view.setBackgroundColor(resources.getColor(R.color.snackbarColor))
        snackbar.show()
    }
}

fun Fragment.setupSnackbar(
    owner: LifecycleOwner,
    observedEvent: LiveData<Event<String>>,
    duration: Int
) {
    observedEvent.observe(owner, { event ->
        event.getContentIfNotHandled()?.let { message ->
            context?.let { showSnackbar(message, duration) }
        }
    })
}
