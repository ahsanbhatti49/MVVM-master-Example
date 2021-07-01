package com.kotlin.mvvm.repository.repo

import androidx.lifecycle.LiveData
import com.kotlin.mvvm.repository.api.ApiServices
import com.kotlin.mvvm.repository.api.network.NetworkResource
import com.kotlin.mvvm.repository.api.network.Resource
import com.kotlin.mvvm.repository.model.FleetModel
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by ahsan on 08,November,2019
 */

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 * https://fake-poi-api.mytaxi.com/?p1Lat=53.694865&p1Lon=9.757589&p2Lat=53.394655&p2Lon=10.099891
 */

@Singleton
class FleetRepository @Inject constructor(
    private val apiServices: ApiServices
) {

    fun getFleetList():
            LiveData<Resource<FleetModel>> {
        val params = HashMap<String, String>()
        params["p1Lat"] = "53.694865"
        params["p1Lon"] = "9.757589"
        params["p2Lat"] = "53.394655"
        params["p2Lon"] = "10.099891"
        return object : NetworkResource<FleetModel>() {
            override fun createCall(): LiveData<Resource<FleetModel>> {
                return apiServices.getFleetList(params)
            }

        }.asLiveData()
    }


}