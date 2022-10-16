package com.udacity.shoestore.utilities.extensions

import androidx.lifecycle.LiveData
import com.udacity.shoestore.utilities.ValidationUtils

/**
 * Returns true if the email [LiveData] is valid
 */
fun LiveData<String>.isValidEmail() : Boolean {
	val emailValue = value ?: ""
	return ValidationUtils.isValidEmail(emailValue)
}
