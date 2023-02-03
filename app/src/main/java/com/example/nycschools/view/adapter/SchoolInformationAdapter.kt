package com.example.nycschools.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.databinding.LetterHolderBinding
import com.example.nycschools.databinding.SchoolsDataHolderBinding
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.utils.ViewType

/**
 * [Class] - School Information Adapter for Nested Recycler View
 */
class SchoolInformationAdapter(
    private val schoolsInfo: MutableList<ViewType> = mutableListOf(),
    private val onClickedSchool: (SchoolInfoResponse) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateSchools(newSchools: List<SchoolInfoResponse>){
        var tempChar = '+'
        val schoolListTemp: MutableList<SchoolInfoResponse> = mutableListOf()
        newSchools.sortedBy { it.schoolName }.forEach { school ->
            val firstLetter = school.schoolName?.first() ?: '+'
            if(firstLetter != tempChar){
                if(schoolListTemp.size>0){
                    schoolsInfo.add(ViewType.SCHOOLS_DATA(SchoolsDataAdapter(schoolListTemp,onClickedSchool)))
                    schoolListTemp.clear()
                }
                schoolsInfo.add(ViewType.LETTER(firstLetter.toString()))
                tempChar = firstLetter
            }
            schoolListTemp.add(school)
        }
        schoolListTemp.clear()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 0){
            LetterViewHolder(
                LetterHolderBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false
                )
            )
        } else{
            SchoolsDataViewHolder(
                SchoolsDataHolderBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false
                )
            )
        }
    }

    override fun getItemCount() = schoolsInfo.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = schoolsInfo[position]){
            is ViewType.LETTER -> {
                (holder as LetterViewHolder).letterDataBinding(item.letter)
            }
            is ViewType.SCHOOLS_DATA -> {
                (holder as SchoolsDataViewHolder).schoolDataBinding(item.schoolsData)
            }
        }
    }

    fun getViewType(position: Int): Int{
        return when(schoolsInfo[position]){
            is ViewType.LETTER -> 0
            is ViewType.SCHOOLS_DATA -> 1
        }
    }

}

class SchoolsDataViewHolder(
    private val binding: SchoolsDataHolderBinding
    ): RecyclerView.ViewHolder(binding.root){

    fun schoolDataBinding(schoolsDataAdapter: SchoolsDataAdapter){
        binding.rvSchoolsData.apply {
            adapter = schoolsDataAdapter
            layoutManager = GridLayoutManager(context,2)
        }
    }
}

class LetterViewHolder(
    private val binding: LetterHolderBinding
): RecyclerView.ViewHolder(binding.root){
    fun letterDataBinding(letter: String){
        binding.tvLetter.text = letter
    }
}