package com.example.nycschools.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nycschools.TAG
import com.example.nycschools.databinding.FragmentSchoolDetailsBinding
import com.example.nycschools.model.SatResultsResponse
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.utils.BaseFragment

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
        schoolsViewModel.schoolsInfoList.observe(viewLifecycleOwner){
            schoolsViewModel.getSchoolItem(schoolsViewModel.itemSelected.value.toString(),it)
            binding.tvSchoolName.text = schoolsViewModel.schoolItem?.schoolName ?: ""
            binding.tvOverviewParagraph.text = schoolsViewModel.schoolItem?.overviewParagraph ?: ""
            binding.btnFindMe.setOnClickListener {
                val gmmUri = Uri.parse(
                    "geo:${schoolsViewModel.schoolItem?.latitude},${schoolsViewModel.schoolItem?.longitude}?z=20")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
        schoolsViewModel.satResultsList.observe(viewLifecycleOwner){
            schoolsViewModel.getSatItem(schoolsViewModel.itemSelected.value.toString(),it)
            binding.tvMathScore.text = schoolsViewModel.satItem?.satMathAvgScore ?: ""
            binding.tvReadingScore.text = schoolsViewModel.satItem?.satCriticalReadingAvgScore ?: ""
            binding.tvWritingScore.text = schoolsViewModel.satItem?.satWritingAvgScore ?: ""
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}