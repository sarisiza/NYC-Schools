package com.example.nycschools.di

import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.rest.SchoolsRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * [Abstract Class] - Module for repository
 */

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideSchoolsRepository(
        schoolsRepositoryImpl: SchoolsRepositoryImpl
    ): SchoolsRepository

}