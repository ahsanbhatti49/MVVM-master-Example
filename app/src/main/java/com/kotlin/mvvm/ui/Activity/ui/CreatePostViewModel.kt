package com.kotlin.mvvm.ui.Activity.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.repository.model.grocery_post.GroceryPost
import com.kotlin.mvvm.repository.repo.grocery.GroceryRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreatePostViewModel @Inject constructor(private val groceryRepository: GroceryRepository) :
    ViewModel() {


    fun insertData(groceryPost: GroceryPost) {
        viewModelScope.launch {
            groceryRepository.insertData(groceryPost)

        }
    }

}