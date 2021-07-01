package com.kotlin.mvvm.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.mvvm.repository.api.network.Resource
import com.kotlin.mvvm.repository.model.FleetModel
import com.kotlin.mvvm.repository.repo.FleetRepository
import javax.inject.Inject

class FleetListViewModel @Inject constructor(private val fleetRepository: FleetRepository) :
    ViewModel() {

    private fun fleetList(): LiveData<Resource<FleetModel>> =
        fleetRepository.getFleetList()

    fun getFleets() = fleetList()

}