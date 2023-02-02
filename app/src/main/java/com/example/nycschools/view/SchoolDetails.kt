package com.example.nycschools.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nycschools.R
import com.example.nycschools.utils.BaseFragment

/**
 * [Fragment] to create School Details
 */
class SchoolDetails : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_details, container, false)
    }

}