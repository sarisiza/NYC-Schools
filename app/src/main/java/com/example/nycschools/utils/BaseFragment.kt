package com.example.nycschools.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.nycschools.di.SchoolsApp
import javax.inject.Inject

open class BaseFragment: Fragment() {

    @Inject
    lateinit var schoolsViewModelFactory: SchoolsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SchoolsApp.schoolsComponent.inject(this)
    }

}