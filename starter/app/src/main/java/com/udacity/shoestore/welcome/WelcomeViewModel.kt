package com.udacity.shoestore.welcome

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.shoestore.R
import com.udacity.shoestore.utilities.DummyItems
import com.udacity.shoestore.welcome.adapter.WelcomeItemModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class WelcomeViewModel : ViewModel() {

	val onBoardingList: List<WelcomeItemModel> = DummyItems.onBoardingList
	private val _currentPageIndexLiveData = MutableLiveData(0)
	val currentPageIndexLiveData: LiveData<Int> = _currentPageIndexLiveData

	@StringRes
	private val _nextButtonTextLiveData = MutableLiveData(R.string.next)
	val buttonTextLiveData: LiveData<Int> = _nextButtonTextLiveData


	private val _onFinishPressed = MutableSharedFlow<Boolean>()
	val onFinishPressed = _onFinishPressed.asSharedFlow()

	fun onNextPressed() {
		if(currentPageIndexLiveData.value!! != onBoardingList.size -1) {
			incrementIndex()
			_nextButtonTextLiveData.value = R.string.next
		} else {
			viewModelScope.launch {
				if(!onFinishPressed.last())
					_nextButtonTextLiveData.value = R.string.finish
				else
					viewModelScope.launch { _onFinishPressed.emit(true) }
			}
		}
	}

	private fun incrementIndex() {
		_currentPageIndexLiveData.value =
			_currentPageIndexLiveData.value?.plus(1)
	}

	fun setCurrentPageIndex(index: Int) {
		_currentPageIndexLiveData.value = index
	}

}
