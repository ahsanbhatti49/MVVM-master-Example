package com.kotlin.mvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView


class GenericAdapter<T : ListItemView>(@LayoutRes val layoutId: Int) :
    RecyclerView.Adapter<GenericAdapter.GenericViewHolder<T>>(), BindableAdapter<T> {

    private val items = mutableListOf<T>()
    private var inflater: LayoutInflater? = null
    private var onListItemViewClickListener: OnListItemViewClickListener? = null
    private var onListSubItemViewClickListener: OnListSubItemViewClickListener? = null
    private var onItemClickWithData: OnItemClickWithData? = null

    private var context: Context? = null
    private var animID: Int? = null
    private var showAnim: Boolean = false
    private var lastPosition = -1

    fun addItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addSingleItem(items: T, position: Int) {
        if (position == 0) {
            this.items.clear()
        }
        this.items.add(items)
        notifyDataSetChanged()
    }

    fun setOnListItemViewClickListener(onListItemViewClickListener: OnListItemViewClickListener?) {
        this.onListItemViewClickListener = onListItemViewClickListener
    }

    fun setOnListSubItemViewClickListener(onListSubItemViewClickListener: OnListSubItemViewClickListener?) {
        this.onListSubItemViewClickListener = onListSubItemViewClickListener
    }

    fun setOnClickWithData(onItemClickWithData: OnItemClickWithData?) {
        this.onItemClickWithData = onItemClickWithData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, layoutId, parent, false)
        return GenericViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        val itemViewModel = items[position]
        itemViewModel.adapterPosition = position
        onListItemViewClickListener?.let { itemViewModel.onListItemViewClickListener = it }
        onListSubItemViewClickListener?.let { itemViewModel.onListSubItemViewClickListener = it }

        holder.bind(itemViewModel)
        if (showAnim) {
            setAnimation(holder.itemView, position)
        }
    }

    class GenericViewHolder<T : ListItemView>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemView: T) {
            binding.setVariable(BR.item, itemView)
            binding.executePendingBindings()

        }
    }

    interface OnListItemViewClickListener {
        fun onClick(view: View, position: Int)
    }

    interface OnListSubItemViewClickListener {
        fun onClick(view: View, position: Int)
    }

    interface OnItemClickWithData {
        fun onClick(view: ListItemView, position: Int)
    }

    override fun setData(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getDataAt(position: Int): Any {
        return this.items.get(position)
    }

    fun showAnim(context: Context, anim: Int) {
        this.context = context
        this.animID = anim
        this.showAnim = true
    }

    private fun setAnimation(
        viewToAnimate: View,
        position: Int
    ) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(context, animID!!)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}
