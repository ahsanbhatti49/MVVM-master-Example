package com.kotlin.mvvm.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.mvvm.di.base.ViewModelFactory
import com.kotlin.mvvm.vm.FleetListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * Created by ahsan on 04,November,2019
 */

@Module
abstract class ViewModelModule {

    /**
     * Countries List View Model
     */
    @Binds
    @IntoMap
    @ViewModelKey(FleetListViewModel::class)
    abstract fun bindFleetListViewModel(fleetListViewModel: FleetListViewModel): ViewModel

    /**
     * Binds ViewModels factory to provide ViewModels.
     */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
