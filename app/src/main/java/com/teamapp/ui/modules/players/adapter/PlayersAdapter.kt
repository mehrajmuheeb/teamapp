package com.teamapp.ui.modules.players.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamapp.data.model.Player
import com.teamapp.databinding.ItemPlayerLayoutBinding
import com.teamapp.ui.base.BaseRecyclerAdapter
import com.teamapp.ui.modules.players.vm.ItemPlayerViewModel

class PlayersAdapter (val list: MutableList<Player>) : BaseRecyclerAdapter<PlayersAdapter.ViewHolder, Player>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemPlayerLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ItemPlayerViewModel(list[position]))
    }

    class ViewHolder(val binding: ItemPlayerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ItemPlayerViewModel) {
            binding.viewModel = viewModel
        }
    }

    fun addItems(list: List<Player>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}