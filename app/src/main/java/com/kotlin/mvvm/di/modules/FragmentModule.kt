package com.kotlin.mvvm.di.modules

import com.kotlin.mvvm.ui.Activity.ui.dashboard.DashboardFragment
import com.kotlin.mvvm.ui.Activity.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    /**
     * Injecting Fragments
     */

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDashboardFragment(): DashboardFragment
}