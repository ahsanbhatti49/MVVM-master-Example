package com.kotlin.mvvm.ui.Activity.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mvvm.R
import com.kotlin.mvvm.repository.model.grocery_post.GroceryItem
import com.kotlin.mvvm.ui.adapter.GenericAdapter
import com.kotlin.mvvm.utils.extensions.gone
import com.kotlin.mvvm.utils.extensions.visible
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by viewModels {
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
        homeViewModel.getGroceryList().observe(viewLifecycleOwner, Observer {
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
                setemptyView()
                adapter.addItems(listOfGrocery)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        rvGroceryList = root.findViewById(R.id.rvGroceryList)
        tvEmpty = root.findViewById(R.id.tvEmpty)

        initUI()

        return root
    }

    private fun initUI() {

        adapter = GenericAdapter(R.layout.item_grocery_list)
        rvGroceryList.layoutManager = LinearLayoutManager(activity)
        rvGroceryList.addItemDecoration(
            DividerItemDecoration(
                rvGroceryList.context,
                DividerItemDecoration.VERTICAL
            )
        )
        rvGroceryList.setHasFixedSize(true)
        adapter.setOnListSubItemViewClickListener(object :
            GenericAdapter.OnListSubItemViewClickListener {
            override fun onClick(view: View, position: Int) {
                homeViewModel.updateStatus(listOfGrocery[position])
                listOfGrocery.removeAt(position)
                adapter.addItems(listOfGrocery)
                setemptyView()

            }
        })
        rvGroceryList.adapter = adapter
        adapter.addItems(listOfGrocery)

    }

    private fun setemptyView() {
        if (listOfGrocery.size > 0) {
            tvEmpty.gone()
            rvGroceryList.visible()
        } else {
            tvEmpty.visible()
            rvGroceryList.gone()
        }
    }


}