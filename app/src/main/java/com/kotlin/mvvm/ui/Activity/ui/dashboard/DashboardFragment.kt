package com.kotlin.mvvm.ui.Activity.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mvvm.R
import com.kotlin.mvvm.repository.model.grocery_post.GroceryItem
import com.kotlin.mvvm.ui.Activity.ui.home.HomeViewModel
import com.kotlin.mvvm.ui.adapter.GenericAdapter
import com.kotlin.mvvm.utils.extensions.gone
import com.kotlin.mvvm.utils.extensions.visible
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DashboardFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val dashboardViewModel: DashboardViewModel by viewModels {
        viewModelFactory
    }

    private var listOfGrocery = ArrayList<GroceryItem>()

    private lateinit var adapter: GenericAdapter<GroceryItem>

    private lateinit var rvGroceryList: RecyclerView

    private lateinit var tvEmpty: TextView

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this) // Providing the dependency
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        fetchGroceryList()
    }

    private fun fetchGroceryList() {
        dashboardViewModel.getGroceryList().observe(viewLifecycleOwner, Observer {
            // You'l get list of countries here
            it?.let {
                listOfGrocery.clear()
                for (grocery in it) {
                    listOfGrocery.add(
                        GroceryItem(
                            id = grocery.id,
                            name = grocery.name,
                            type = grocery.type,
                            status = grocery.status
                        )
                    )
                }
                if (listOfGrocery.size > 0) {
                    tvEmpty.gone()
                    rvGroceryList.visible()
                } else {
                    tvEmpty.visible()
                    rvGroceryList.gone()
                }
                adapter.addItems(listOfGrocery)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        rvGroceryList = root.findViewById(R.id.rvGroceryList)
        tvEmpty = root.findViewById(R.id.tvEmpty)

        initUI()
        return root
    }

    private fun initUI() {
        adapter = GenericAdapter(R.layout.item_grocery_list_deleted)
        rvGroceryList.layoutManager = LinearLayoutManager(activity)
        rvGroceryList.addItemDecoration(
            DividerItemDecoration(
                rvGroceryList.context,
                DividerItemDecoration.VERTICAL
            )
        )
        rvGroceryList.setHasFixedSize(true)
        rvGroceryList.adapter = adapter
        adapter.addItems(listOfGrocery)
    }
}