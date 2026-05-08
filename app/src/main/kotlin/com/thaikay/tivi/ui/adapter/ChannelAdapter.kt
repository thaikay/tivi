package com.thaikay.tivi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thaikay.tivi.data.model.Channel
import com.thaikay.tivi.databinding.ItemChannelBinding

class ChannelAdapter(
    private val onChannelClick: (Channel) -> Unit
) : ListAdapter<Channel, ChannelAdapter.ChannelViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        return ChannelViewHolder(
            ItemChannelBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onChannelClick
        )
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ChannelViewHolder(
        private val binding: ItemChannelBinding,
        private val onChannelClick: (Channel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(channel: Channel) {
            binding.apply {
                tvChannelName.text = channel.name
                tvChannelGroup.text = channel.group.ifEmpty { "No Group" }

                if (channel.logo.isNotEmpty()) {
                    Glide.with(ivChannelLogo)
                        .load(channel.logo)
                        .centerCrop()
                        .into(ivChannelLogo)
                }

                root.setOnClickListener { onChannelClick(channel) }
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Channel>() {
            override fun areItemsTheSame(oldItem: Channel, newItem: Channel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Channel, newItem: Channel) =
                oldItem == newItem
        }
    }
}
