package com.example.nycschools.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * [Class] - Module that provides application context
 */

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideContext(): Context = application.applicationContext

}