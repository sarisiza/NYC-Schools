package com.example.nycschools.di

import android.app.Application

class SchoolsApp: Application() {

    override fun onCreate() {
        super.onCreate()

    }

    companion object{
        lateinit var schoolsComponent: SchoolsComponent
    }

}