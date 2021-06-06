package com.kotlin.mvvm.repository.repo.grocery

import android.content.Context
import com.kotlin.mvvm.repository.db.grocery_dao.GroceryDao
import com.kotlin.mvvm.repository.model.grocery_post.GroceryItem
import com.kotlin.mvvm.repository.model.grocery_post.GroceryPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by ahsan on 08,November,2019
 */

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 */

@Singleton
class GroceryRepository @Inject constructor(
    private val groceryDao: GroceryDao,
    private val context: Context
) {

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    suspend fun getGroceries(): List<GroceryPost> {
        return groceryDao.getAllList()
    }

    suspend fun getCompletedItems(): List<GroceryPost> {
        return groceryDao.getCompletedList()
    }

    suspend fun insertData(groceryPost: GroceryPost) {
        groceryDao.insertCountries(groceryPost)
    }

    suspend fun updateData(groceryItem: GroceryItem) {
        withContext(Dispatchers.IO) {
            groceryDao.updateStatus(
                GroceryPost(
                    id = groceryItem.id,
                    name = groceryItem.name,
                    type = groceryItem.type,
                    status = "Completed"
                )
            )
        }
    }

}