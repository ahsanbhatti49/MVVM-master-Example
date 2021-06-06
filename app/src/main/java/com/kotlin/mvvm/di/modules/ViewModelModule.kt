package com.kotlin.mvvm.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.mvvm.di.base.ViewModelFactory
import com.kotlin.mvvm.ui.Activity.ui.CreatePostViewModel
import com.kotlin.mvvm.ui.Activity.ui.dashboard.DashboardViewModel
import com.kotlin.mvvm.ui.Activity.ui.home.HomeViewModel
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
    @ViewModelKey(CreatePostViewModel::class)
    abstract fun bindCreatePostViewModel(createPostViewModel: CreatePostViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel

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
