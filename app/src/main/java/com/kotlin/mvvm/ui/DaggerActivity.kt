package com.kotlin.mvvm.ui

import androidx.lifecycle.ViewModelProvider
import com.kotlin.mvvm.repository.model.SharedViewModel
import com.kotlin.mvvm.utils.extensions.getViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by ahsan on 04,November,2019
 * Updated to dagger 2.27, 29, September 2020
 */

// Referring this class as BaseActivity

typealias BaseActivity = DaggerActivity

/**
 * Activity providing Dagger support and [ViewModel] support
 */
abstract class DaggerActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    public val sharedViewModel by lazy {
        getViewModel<SharedViewModel>()
    }
}
