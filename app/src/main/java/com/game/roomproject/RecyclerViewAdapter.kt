package com.game.roomproject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.game.roomproject.data.Subscriber
import com.game.roomproject.databinding.ListItemBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class RecyclerViewAdapter(
    private val context: Context,
    private val subscribers: List<Subscriber>,
    private val clickListener: (Subscriber) -> Unit
) : BaseAdapter<Subscriber, ListItemBinding>() {
    override fun getAdapterBinding(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ListItemBinding> {
        val binding = ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ListItemBinding>, position: Int) {
        holder.binding.nameTextView.text = subscribers[position].name
        holder.binding.emailTextView.text = subscribers[position].email
        holder.binding.listItemLayout.setOnClickListener {
            clickListener(subscribers[position])
        }
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }
}