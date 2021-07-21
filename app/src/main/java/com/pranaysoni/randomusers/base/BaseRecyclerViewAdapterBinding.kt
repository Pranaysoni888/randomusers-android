package com.pranaysoni.randomusers.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapterBinding<VDB:ViewDataBinding>(private val items: ArrayList<out Any?>):RecyclerView.Adapter<BaseRecyclerViewAdapterBinding.ItemViewHolder<VDB>>() {

    protected abstract fun getDataBinding(viewType: Int,parent: ViewGroup):VDB

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(getDataBinding(viewType, parent))

    class ItemViewHolder<VDB:ViewDataBinding>(binding: VDB) : RecyclerView.ViewHolder(binding.root){
        var dataBinding: VDB = binding
    }
}