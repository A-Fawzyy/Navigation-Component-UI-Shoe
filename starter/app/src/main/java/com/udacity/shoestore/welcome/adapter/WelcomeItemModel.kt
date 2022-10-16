package com.udacity.shoestore.welcome.adapter

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class WelcomeItemModel(

	/**
	 * Resource Id of the title
	 */
	@StringRes
	val titleResId: Int,

	/**
	 * Resource Id of the description
	 */
	@StringRes
	val descriptionResId: Int,

	/**
	 * Resource Id of the background image
	 */
	@DrawableRes
	val ImageResId: Int,

)
