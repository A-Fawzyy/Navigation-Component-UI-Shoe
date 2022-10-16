package com.udacity.shoestore.welcome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeListItemBinding
import com.udacity.shoestore.welcome.WelcomeViewModel

class WelcomeAdapter(private val _viewModel: WelcomeViewModel) :
	RecyclerView.Adapter<WelcomeAdapter.ViewHolder>() {


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding: WelcomeListItemBinding =
			DataBindingUtil.inflate(
				LayoutInflater.from(parent.context),
				R.layout.welcome_list_item, parent, false,
			)

		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = _viewModel.onBoardingList[position]
		holder.bind(item)
	}

	override fun getItemCount() = _viewModel.onBoardingList.count()


	inner class ViewHolder(private val binding: WelcomeListItemBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: WelcomeItemModel) {
			binding.viewModel = _viewModel
			binding.item = item
		}
	}
}
