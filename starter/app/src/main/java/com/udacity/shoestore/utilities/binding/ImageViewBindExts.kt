package com.udacity.shoestore.utilities.binding

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

/**
 * sets the image resource id to [imageResId] using [ImageView.setImageResource]
 */
@BindingAdapter("imgResId")
fun ImageView.setImageResId(@DrawableRes imageResId: Int) {
	setImageResource(imageResId)
}
