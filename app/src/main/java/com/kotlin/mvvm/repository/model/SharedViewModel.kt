package com.kotlin.mvvm.repository.model

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    val data = MutableLiveData<Any>()

    fun setData(data: Bundle) {
        this.data.value = data
    }
}