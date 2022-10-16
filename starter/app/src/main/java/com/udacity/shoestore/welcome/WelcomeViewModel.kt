package com.udacity.shoestore.welcome

import androidx.lifecycle.*
import com.udacity.shoestore.utilities.DummyItems
import com.udacity.shoestore.welcome.adapter.WelcomeItemModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class WelcomeViewModel : ViewModel() {

	val onBoardingList: List<WelcomeItemModel> = DummyItems.onBoardingList
	val _currentPageIndexLiveData = MutableLiveData(0)
	val currentPageIndexLiveData: LiveData<Int> = _currentPageIndexLiveData

	/**
	 * Carries the visibility state of the Skip Button
	 *
	 * hides the button in case it's the last page
	 */
	val isSkipVisibleLiveData: LiveData<Boolean>
		get() = Transformations.map(currentPageIndexLiveData) {
			it != onBoardingList.size - 1
		}

	val isNextVisibleLiveData: LiveData<Boolean>
		get() = Transformations.map(_currentPageIndexLiveData) {
			it != onBoardingList.size - 1
		}

	val isStartVisibleLiveData: LiveData<Boolean>
		get() = Transformations.map(_currentPageIndexLiveData) {
			it == onBoardingList.size - 1
		}
/*
	*/
	/**
	 * Carries the String resource of the Next/Done Button
	 *
	 * in case it's the last page, the button is set to Start
	val nextButtonTextLiveData: LiveData<Int> = Transformations.map(currentPageIndexLiveData) {
	if (it == onBoardingList.size)
	return@map R.string.start
	else
	return@map R.string.next
	}*/


	private val _onStartPressed = MutableSharedFlow<Boolean>()
	val onStartPressed = _onStartPressed.asSharedFlow()


	private val _onNextPressed = MutableSharedFlow<Boolean>()
	val onNextPressed = _onNextPressed.asSharedFlow()


	private val _onSkipPressed = MutableSharedFlow<Boolean>()
	val onSkipPressed = _onSkipPressed.asSharedFlow()

	fun emitOnStartPressed() {
		viewModelScope.launch { _onStartPressed.emit(true) }
	}

	fun emitOnNextPressed() {
		viewModelScope.launch { _onNextPressed.emit(true) }
	}

	fun emitOnSkipPressed() {
		viewModelScope.launch { _onSkipPressed.emit(true) }
	}

	private fun incrementIndex() {
		_currentPageIndexLiveData.value =
			_currentPageIndexLiveData.value?.plus(1)
	}

	fun setCurrentPageIndex(index: Int) {
		_currentPageIndexLiveData.value = index
	}

}
