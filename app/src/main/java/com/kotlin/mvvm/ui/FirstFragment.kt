package com.kotlin.mvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.FragmentFirstBinding
import com.kotlin.mvvm.repository.model.Poi
import com.kotlin.mvvm.repository.model.SharedViewModel
import com.kotlin.mvvm.ui.adapter.GenericAdapter
import com.kotlin.mvvm.utils.extensions.gone
import com.kotlin.mvvm.utils.extensions.visible
import com.kotlin.mvvm.vm.FleetListViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_first.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private val fleetListViewModel: FleetListViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var adapter: GenericAdapter<Poi>
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var fleetData: MutableList<Poi>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        getFleetList()
        return binding.root

    }

    fun getShareViewModel(): SharedViewModel? {
        activity?.let {

            sharedViewModel = ViewModelProvider(it).get(SharedViewModel::class.java)
        }
        return sharedViewModel
    }


    private fun getFleetList() {
        fleetListViewModel.getFleets().observe(viewLifecycleOwner, {
            when {
                it.status.isError() -> {
                    Log.d("Error", "Error")
                }
                it.status.isLoading() -> {
                    binding.progressLayout.progressView.visible()
                }
                it.status.isSuccessful() -> {
                    this.fleetData = it.data!!.poiList as MutableList<Poi>
                    binding.progressLayout.progressView.gone()
                    if (it.data!!.poiList.isEmpty())
                        binding.emptyLayout.emptyView.visible()
                    else
                        binding.emptyLayout.emptyView.gone()
                    adapter.setData(it.data!!.poiList)
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFleetListView()
    }

    private fun initFleetListView() {
        adapter = GenericAdapter(R.layout.row_item_fleet)
        binding.fleetList.layoutManager = LinearLayoutManager(activity)
        binding.fleetList.addItemDecoration(
            DividerItemDecoration(
                fleet_list.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.fleetList.setHasFixedSize(true)
        adapter.setOnListItemViewClickListener(object :
            GenericAdapter.OnListItemViewClickListener {
            override fun onClick(view: View, position: Int) {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                val bundle = Bundle()
                bundle.putString("lat", fleetData.get(position).coordinate.latitude.toString())
                bundle.putString("long", fleetData.get(position).coordinate.longitude.toString())
                bundle.putString("type", fleetData.get(position).fleetType)
                getShareViewModel()!!.setData(bundle)
            }
        })
        binding.fleetList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}