package com.kotlin.mvvm.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.mvvm.repository.db.grocery_dao.GroceryDao
import com.kotlin.mvvm.repository.model.grocery_post.GroceryPost

/**
 * Created by ahsan on 04,November,2019
 */

/**
 * App Database
 * Define all entities and access doa's here/ Each entity is a table.
 */
@Database(
    entities = [GroceryPost::class],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Get DAO's
     */

    abstract fun groceryPostDao(): GroceryDao

}