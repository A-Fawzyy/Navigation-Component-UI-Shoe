package com.udacity.shoestore.utilities.binding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.setIsVisible(isVisible: Boolean, oldIsVisible: Boolean) {
	if(isVisible != oldIsVisible)
		this.isVisible = isVisible
}
