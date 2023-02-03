package com.example.nycschools.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.TAG
import com.example.nycschools.databinding.SchoolInformationBinding
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.model.SchoolItem

/**
 * [Class] - Defines the adapter for the School information
 */
class SchoolsDataAdapter(
    private val schoolsList: MutableList<SchoolInfoResponse>,
    private val onClickedSchool: (SchoolInfoResponse) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init{
        Log.d(TAG, "SchoolsDataAdapter: creating adapter")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SchoolViewHolder(
            SchoolInformationBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount() = schoolsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: getting here")
        (holder as SchoolViewHolder).schoolBinding(schoolsList[position],onClickedSchool)
    }

}

class SchoolViewHolder(private val binding: SchoolInformationBinding): RecyclerView.ViewHolder(binding.root){

    fun schoolBinding(school: SchoolInfoResponse, onClickedSchool: (SchoolInfoResponse) -> Unit){
        binding.tvSchoolName.text = school.schoolName
        binding.tvEmail.text = school.schoolEmail
        binding.tvLocation.text = school.location
        binding.tvPhone.text = school.phoneNumber
        binding.tvWebpage.text = school.website
        itemView.setOnClickListener {
            onClickedSchool(school)
        }
    }

}