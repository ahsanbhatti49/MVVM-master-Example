package com.kotlin.mvvm.di.modules

import com.kotlin.mvvm.ui.FirstFragment
import com.kotlin.mvvm.ui.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    /**
     * Injecting Fragments
     */
    @ContributesAndroidInjector
    internal abstract fun contributeFirstFragment(): FirstFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSecondFragment(): SecondFragment
}