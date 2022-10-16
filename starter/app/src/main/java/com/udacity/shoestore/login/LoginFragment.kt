package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.utilities.extensions.collectLatestOnce
import timber.log.Timber

class LoginFragment : Fragment() {

	private var _binding: FragmentLoginBinding? = null
	private val viewModel: LoginViewModel by viewModels()

	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentLoginBinding.inflate(inflater)
		binding.viewModel = viewModel
		binding.lifecycleOwner = this
		val view = binding.root
		setUpObservers()
		return view
	}

	private fun setUpObservers() {
		viewModel.emailLiveData.observe(viewLifecycleOwner) {
			Timber.i(it)
		}
		viewModel.passwordLiveData.observe(viewLifecycleOwner) {
			Timber.i(it)
		}

		viewModel.onFormSubmitted.collectLatestOnce(viewLifecycleOwner) {
			val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
				viewModel.emailLiveData.value ?: ""
			)
			findNavController().navigate(action)
		}
	}
}
