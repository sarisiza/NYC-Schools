package com.example.nycschools.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nycschools.di.SchoolsApp
import com.example.nycschools.viewmodel.SchoolsViewModel
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var schoolsViewModelFactory: SchoolsViewModelFactory
    var currentDbn = ""

    protected val schoolsViewModel: SchoolsViewModel by lazy {
        ViewModelProvider(requireActivity(), schoolsViewModelFactory)[SchoolsViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SchoolsApp.schoolsComponent.inject(this)
    }

}