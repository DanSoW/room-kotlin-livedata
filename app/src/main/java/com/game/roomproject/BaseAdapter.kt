package com.game.roomproject

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, B: ViewBinding>(): RecyclerView.Adapter<BaseAdapter<T, B>.BaseViewHolder<B>>() {
    inner class BaseViewHolder<VB: ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> {
        return getAdapterBinding(parent, viewType)
    }

    abstract fun getAdapterBinding(parent: ViewGroup, viewType: Int) : BaseViewHolder<B>
}