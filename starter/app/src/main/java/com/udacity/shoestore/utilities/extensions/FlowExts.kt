package com.udacity.shoestore.utilities.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

/**
 * Allows consuming the event only once
 * Used for snackBars, toasts, navigation events
 */
fun <T> Flow<T>.collectLatestOnce(lifecycleOwner:LifecycleOwner, collectionBlock: (T) -> Unit) {
	lifecycleOwner.lifecycleScope.launchWhenStarted {
		collectLatest {
			collectionBlock.invoke(it)
		}
	}
}
