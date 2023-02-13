package com.example.nycschools.di

import com.example.nycschools.database.LocalRepository
import com.example.nycschools.database.LocalRepositoryImpl
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.rest.SchoolsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * [Abstract Class] - Module for repository
 */

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideSchoolsRepository(
        schoolsRepositoryImpl: SchoolsRepositoryImpl
    ): SchoolsRepository

    @Binds
    abstract fun provideLocalSchoolsRepository(
        schoolsRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository

}