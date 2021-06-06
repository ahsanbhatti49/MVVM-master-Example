package com.kotlin.mvvm.di.modules

import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.kotlin.mvvm.BuildConfig
import com.kotlin.mvvm.app.App
import com.kotlin.mvvm.repository.api.ApiServices
import com.kotlin.mvvm.repository.api.network.LiveDataCallAdapterFactoryForRetrofit
import com.kotlin.mvvm.repository.db.AppDatabase
import com.kotlin.mvvm.repository.db.grocery_dao.GroceryDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by ahsan on 04,November,2019
 */

@Module(includes = [PreferencesModule::class, ActivityModule::class, ViewModelModule::class])
class AppModule {

    /**
     * Static variables to hold base url's etc.
     */
    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL
    }


    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    fun provideNewsService(): ApiServices {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
            .build()
            .create(ApiServices::class.java)
    }


    /**
     * Provides app AppDatabase
     */
    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "news-db")
            .fallbackToDestructiveMigration().build()


    /**
     * Provides GroceryDao an object to access Countries table from Database
     */
    @Singleton
    @Provides
    fun provideGroceryDao(db: AppDatabase): GroceryDao = db.groceryPostDao()


    /**
     * Application application level context.
     */
    @Singleton
    @Provides
    fun provideContext(application: App): Context = application.applicationContext


    /**
     * Application resource provider, so that we can get the Drawable, Color, String etc at runtime
     */
    @Provides
    @Singleton
    fun providesResources(application: App): Resources = application.resources
}
