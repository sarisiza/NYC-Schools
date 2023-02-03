package com.example.nycschools.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nycschools.databinding.FragmentSchoolInformationBinding
import com.example.nycschools.utils.BaseFragment
import com.example.nycschools.utils.UIState


class SchoolInformation : BaseFragment() {

    private val binding by lazy {
        FragmentSchoolInformationBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        schoolsViewModel.schoolsInfo.observe(viewLifecycleOwner){ state ->
            when(state){
                is UIState.LOADING -> {}
                is UIState.SUCCESS<*> -> {}
                is UIState.ERROR -> {}
            }
        }
        return binding.root
    }
}