package com.udacity.shoestore.utilities

import androidx.core.util.PatternsCompat

/**
 * Used to Validate various input types
 */
object ValidationUtils {
	/**
	 * Returns true if [email] is a valid email
	 */
	fun isValidEmail(email: String): Boolean {
		return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
	}

	/**
	 * Returns true if [phoneNumber] is a valid phoneNumber
	 */
	fun isValidPhone(phoneNumber: String): Boolean {
		TODO()
	}

}
