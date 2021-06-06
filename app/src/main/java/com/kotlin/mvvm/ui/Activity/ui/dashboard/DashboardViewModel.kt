package com.kotlin.mvvm.ui.Activity.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.repository.model.grocery_post.GroceryPost
import com.kotlin.mvvm.repository.repo.grocery.GroceryRepository
import kotlinx.coroutines.launch

class DashboardViewModel @javax.inject.Inject constructor(val groceryRepository: GroceryRepository) :
    ViewModel() {

    private var groceryList: MutableLiveData<List<GroceryPost>> = MutableLiveData()

    fun getGroceryList(): LiveData<List<GroceryPost>> {
        viewModelScope.launch {
            val result = groceryRepository.getCompletedItems()
            groceryList.postValue(result)
        }
        return groceryList
    }

}