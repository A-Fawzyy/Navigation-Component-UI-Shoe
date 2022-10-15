package com.udacity.shoestore.login

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.shoestore.R
import com.udacity.shoestore.utilities.extensions.isValidEmail
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
	val emailLiveData = MutableLiveData<String>()
	val passwordLiveData = MutableLiveData<String>()

	private val _onFormSubmitted = MutableSharedFlow<Boolean>()
	val onFormSubmitted = _onFormSubmitted.asSharedFlow()

	@StringRes
	private val _emailErrorLiveData = MutableLiveData(-1)
	val emailErrorLiveData: LiveData<Int> get() = _emailErrorLiveData

	@StringRes
	private val _passwordErrorLiveData = MutableLiveData(-1)
	val passwordErrorLiveData: LiveData<Int> get() = _passwordErrorLiveData

	fun validateInputs() {
		var isFormValid = true
		if(emailLiveData.value.isNullOrBlank()) {
			_emailErrorLiveData.value = R.string.please_enter_your_email
			isFormValid = false
		} else if(!emailLiveData.isValidEmail()) {
			_emailErrorLiveData.value = R.string.invalid_email
			isFormValid = false
		} else {
			_emailErrorLiveData.value = -1
		}

		if(passwordLiveData.value.isNullOrBlank()) {
			_passwordErrorLiveData.value = R.string.please_enter_your_password
			isFormValid = false
		} else {
			_passwordErrorLiveData.value = -1
		}

		if(isFormValid)
			viewModelScope.launch { _onFormSubmitted.emit(true) }
	}
}
