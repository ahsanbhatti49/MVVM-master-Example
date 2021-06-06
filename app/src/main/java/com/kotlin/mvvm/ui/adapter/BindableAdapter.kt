package com.kotlin.mvvm.ui.adapter

interface BindableAdapter<T> {
    fun setData(items: List<T>)
}