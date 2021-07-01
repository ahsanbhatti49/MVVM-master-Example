package com.kotlin.mvvm.repository.api


import androidx.lifecycle.LiveData
import com.kotlin.mvvm.repository.api.network.Resource
import com.kotlin.mvvm.repository.model.FleetModel
import retrofit2.http.*

/**
 * Created by ahsan on 04,November,2019
 */
interface ApiServices {
    @GET(".")
    fun getFleetList(@QueryMap options: Map<String, String>): LiveData<Resource<FleetModel>>

}
