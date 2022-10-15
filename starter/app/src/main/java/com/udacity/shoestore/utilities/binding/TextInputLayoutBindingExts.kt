package com.udacity.shoestore.utilities.binding

import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

/**
 * Toggles the error based on [errorResId]
 *
 * in case [errorResId] is null, hides the error
 */
@BindingAdapter("toggleError")
fun TextInputLayout.toggleTextInputError(@StringRes errorResId: Int) {
	/// we set the resId to -1 when we want to empty the error
	if(errorResId == -1) {
		isErrorEnabled = false
		error = ""
	} else {
		isErrorEnabled = true
		error = context.getString(errorResId)
	}
}

