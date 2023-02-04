package com.example.nycschools.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.TAG
import com.example.nycschools.databinding.SchoolInformationBinding
import com.example.nycschools.model.SchoolInfoResponse

/**
 * [Class] - Defines the adapter for the School information
 * Child Adapter
 */
class SchoolsDataAdapter(
    private val schoolsList: MutableList<SchoolInfoResponse> = mutableListOf(),
    private val onClickedSchool: (String) -> Unit
): RecyclerView.Adapter<SchoolViewHolder>() {


    fun updateSchools(newSchools: List<SchoolInfoResponse>){
        if(schoolsList != newSchools){
            schoolsList.addAll(newSchools)

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(
            SchoolInformationBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount() = schoolsList.size

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.schoolBinding(schoolsList[position],onClickedSchool)
    }

}

class SchoolViewHolder(private val binding: SchoolInformationBinding): RecyclerView.ViewHolder(binding.root){

    fun schoolBinding(school: SchoolInfoResponse, onClickedSchool: (String) -> Unit){
        binding.tvSchoolName.text = school.schoolName
        binding.tvEmail.text = school.schoolEmail
        binding.tvLocation.text = school.location
        binding.tvPhone.text = school.phoneNumber
        binding.tvWebpage.text = school.website
        itemView.setOnClickListener {
            school.dbn?.let(onClickedSchool)
        }
    }

}