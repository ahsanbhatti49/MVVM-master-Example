package com.kotlin.mvvm.ui.Activity.ui

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.kotlin.mvvm.R
import com.kotlin.mvvm.repository.model.grocery_post.GroceryPost
import com.kotlin.mvvm.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : BaseActivity() {

    private val createPostViewModel: CreatePostViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            createPostViewModel.insertData(
                GroceryPost(
                    name = etName.text.toString(),
                    status = etStatus.text.toString(),
                    type = etQty.text.toString()
                )
            )

            finish()
        }
    }
}