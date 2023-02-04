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

    private var schoolItem: SchoolInfoResponse = SchoolInfoResponse()
    private var satItem: SatResultsResponse = SatResultsResponse()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        schoolsViewModel.schoolsInfoList.observe(viewLifecycleOwner){
            getSchoolItem(schoolsViewModel.itemSelected.value.toString(),it)
            binding.tvSchoolName.text = schoolItem.schoolName ?: ""
            binding.tvOverviewParagraph.text = schoolItem.overviewParagraph ?: ""
            binding.btnFindMe.setOnClickListener {
                val gmmUri = Uri.parse(
                    "geo:${schoolItem.latitude},${schoolItem.longitude}?z=20")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
        schoolsViewModel.satResultsList.observe(viewLifecycleOwner){
            getSatItem(schoolsViewModel.itemSelected.value.toString(),it)
            binding.tvMathScore.text = satItem.satMathAvgScore ?: ""
            binding.tvReadingScore.text = satItem.satCriticalReadingAvgScore ?: ""
            binding.tvWritingScore.text = satItem.satWritingAvgScore ?: ""
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getSchoolItem(dbn: String, schoolList: List<SchoolInfoResponse>?){
        var found = false
        var i = 0
        while (!found && i < (schoolList?.size ?: 0)){
            if(schoolList?.get(i)?.dbn == dbn){
                schoolItem = schoolList[i]
                found = true
            }
            i++
        }
    }

    private fun getSatItem(dbn: String, satList: List<SatResultsResponse>?){
        var found = false
        var i = 0
        while (!found && i < (satList?.size ?: 0)){
            if(satList?.get(i)?.dbn == dbn){
                satItem = satList[i]
                found = true
            }
            i++
        }
    }

}