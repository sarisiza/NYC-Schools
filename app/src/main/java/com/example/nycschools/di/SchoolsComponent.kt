package com.example.nycschools.di

import com.example.nycschools.MainActivity
import dagger.Component

/**
 * [Interface] - specifies the Dependency Injection components
 */

@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    ApplicationModule::class
])
interface SchoolsComponent {

    /**
     * Method to inject dependencies in the Main Activity
     */
    fun inject(mainActivity: MainActivity)

}