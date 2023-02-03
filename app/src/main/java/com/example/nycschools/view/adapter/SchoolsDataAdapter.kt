package com.example.nycschools.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.databinding.SchoolInformationBinding
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.model.SchoolItem

/**
 * [Class] - Defines the adapter for the School information
 */
class SchoolsDataAdapter(
    private val schoolsList: MutableList<SchoolInfoResponse> = mutableListOf()):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SchoolViewHolder(
            SchoolInformationBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount() = schoolsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SchoolViewHolder).schoolBinding(schoolsList[position])
    }

}

class SchoolViewHolder(private val binding: SchoolInformationBinding): RecyclerView.ViewHolder(binding.root){

    fun schoolBinding(school: SchoolInfoResponse){
        binding.tvSchoolName.text = school.schoolName
        binding.tvEmail.text = school.schoolEmail
        binding.tvLocation.text = school.location
        binding.tvPhone.text = school.phoneNumber
        binding.tvWebpage.text = school.website
    }

}