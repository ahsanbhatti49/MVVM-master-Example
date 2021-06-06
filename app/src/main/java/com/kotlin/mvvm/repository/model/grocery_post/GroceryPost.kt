package com.kotlin.mvvm.repository.model.grocery_post

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kotlin.mvvm.ui.adapter.ListItemView

@Entity(tableName = "grocery_post_table")
data class GroceryPost(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("status") var status: String? = null,
)

data class GroceryItem(
    var id: Int = 0,
    var name: String? = null,
    var type: String? = null,
    var status: String? = null,
) : ListItemView()

