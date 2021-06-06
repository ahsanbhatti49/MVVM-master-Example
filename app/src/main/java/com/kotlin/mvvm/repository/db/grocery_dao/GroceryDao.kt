package com.kotlin.mvvm.repository.db.grocery_dao

import androidx.room.*
import com.kotlin.mvvm.repository.model.grocery_post.GroceryPost

/**
 * Created by ahsan on 04,November,2019
 */

/**
 * Abstracts access to the Grocery database
 */
@Dao
abstract class GroceryDao {


    /**
     * Insert countries into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCountries(groceryPost: GroceryPost)

    /**
     * Get all countries from database
     */
    @Query("SELECT * FROM grocery_post_table where status=='Completed'")
    abstract suspend fun getCompletedList(): List<GroceryPost>

    @Query("SELECT * FROM grocery_post_table where status!='Completed'")
    abstract suspend fun getAllList(): List<GroceryPost>


    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateStatus(groceryPost: GroceryPost)

    /**
     * Delete all countries from database
     */
    @Query("DELETE FROM grocery_post_table")
    abstract suspend fun deleteAllList()

//    @Query("UPDATE  FROM grocery_post_table")
//    abstract fun updateData(groceryPost: GroceryPost)
}