package com.example.nycschools.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nycschools.R
import com.example.nycschools.databinding.FragmentSchoolDetailsBinding
import com.example.nycschools.model.SatResultsResponse
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.utils.BaseFragment
import com.example.nycschools.utils.UIState

/**
 * [Fragment] to create School Details
 */
class SchoolDetails : BaseFragment() {

    private val binding by lazy{
        FragmentSchoolDetailsBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var schoolItem: SchoolInfoResponse
        var satItem: SatResultsResponse
        /*
        binding.tvSchoolName.text = schoolItem.schoolName
        binding.tvOverviewParagraph.text = schoolItem.overviewParagraph
        binding.tvMathScore.text = satItem.satMathAvgScore
        binding.tvReadingScore.text = satItem.satCriticalReadingAvgScore
        binding.tvWritingScore.text = satItem.satWritingAvgScore
        }*/
        // Inflate the layout for this fragment
        return binding.root
    }

}