package com.kotlin.mvvm.ui.Activity.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.repository.model.grocery_post.GroceryItem
import com.kotlin.mvvm.repository.model.grocery_post.GroceryPost
import com.kotlin.mvvm.repository.repo.grocery.GroceryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(val groceryRepository: GroceryRepository) :
    ViewModel() {

    private var groceryList: MutableLiveData<List<GroceryPost>> = MutableLiveData()

    fun getGroceryList(): LiveData<List<GroceryPost>> {
        viewModelScope.launch {
            val result = groceryRepository.getGroceries()
            groceryList.postValue(result)
        }
        return groceryList
    }


    fun updateStatus(groceryItem: GroceryItem) {
        viewModelScope.launch {
            groceryRepository.updateData(groceryItem)
        }
    }
}