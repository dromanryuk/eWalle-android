package ru.dromanryuk.ewalle.presentation.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Fragment.observeWhileStarted(flow: Flow<T>, action: suspend (T) -> Unit) {
    flow
        .flowWithLifecycle(viewLifecycleOwner.lifecycle)
        .onEach(action)
        .launchIn(viewLifecycleOwner.lifecycleScope)
}