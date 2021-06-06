package com.kotlin.mvvm.ui.adapter

abstract class ListItemView {
    var adapterPosition: Int = -1
    var onListItemViewClickListener: GenericAdapter.OnListItemViewClickListener? = null
    var onListSubItemViewClickListener: GenericAdapter.OnListSubItemViewClickListener? = null
}