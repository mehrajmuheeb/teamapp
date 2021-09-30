package com.teamapp.ui.modules.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamapp.databinding.ItemTeamLayoutBinding
import com.teamapp.ui.base.BaseRecyclerAdapter
import com.teamapp.ui.modules.dashboard.vm.ItemTeamViewModel

class TeamAdapter(val list: MutableList<String>, val listener: (index: String)->Unit) : BaseRecyclerAdapter<TeamAdapter.ViewHolder, String>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder=
        ViewHolder(
            ItemTeamLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ItemTeamViewModel(list[position],  listener))
    }

    class ViewHolder(val binding: ItemTeamLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ItemTeamViewModel) {
            binding.viewModel = viewModel
        }
    }

    fun addItems(list: List<String>) {
        this.list.addAll(list)
    }

}