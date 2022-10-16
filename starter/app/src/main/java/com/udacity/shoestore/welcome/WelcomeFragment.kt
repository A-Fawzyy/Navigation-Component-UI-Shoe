package com.udacity.shoestore.welcome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.utilities.extensions.collectLatestOnce
import com.udacity.shoestore.welcome.adapter.WelcomeAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WelcomeFragment : Fragment() {

	val args: WelcomeFragmentArgs by navArgs()
	val viewModel: WelcomeViewModel? by viewModels()
	private var _binding: FragmentWelcomeBinding? = null
	private val binding get() = _binding!!
	private lateinit var welcomeAdapter: WelcomeAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		_binding = FragmentWelcomeBinding.inflate(inflater)
		binding.viewModel = viewModel
		binding.lifecycleOwner = this
		val view = binding.root
		setUpObservers()
		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.apply {
			welcomeAdapter = WelcomeAdapter(viewModel!!)
			setUpViewPager()

		}
	}

	private fun setUpObservers() {
		viewLifecycleOwner.lifecycleScope.launch {
			viewModel?.onStartPressed?.collectLatestOnce(viewLifecycleOwner) {
				Log.i("Finish", "setUpObservers: $it")
				// da tamam
			}

			viewModel?.onNextPressed?.collectLatest {
				onNextPressed()
			}

			viewModel?.onStartPressed?.collectLatest {
			}

			viewModel?.onSkipPressed?.collectLatest {
			}

			viewModel?.currentPageIndexLiveData?.observe(viewLifecycleOwner) {

			}
		}

		viewModel?._currentPageIndexLiveData?.observe(viewLifecycleOwner) {

		}
	}

	private fun setUpViewPager() {
		binding.apply {
			viewPager.adapter = welcomeAdapter
			viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
				override fun onPageSelected(position: Int) {
					viewModel?.setCurrentPageIndex(position)
					super.onPageSelected(position)
				}
			})
		}
	}

	private fun onNextPressed() {
		binding.viewPager.currentItem = viewModel?.currentPageIndexLiveData?.value?.plus(1) ?: 0
	}
}
