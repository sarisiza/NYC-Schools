package com.example.nycschools.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.example.nycschools.TAG
import com.example.nycschools.databinding.LetterHolderBinding
import com.example.nycschools.databinding.SchoolsDataHolderBinding
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.utils.ViewType

/**
 * [Class] - School Information Adapter for Nested Recycler View
 */
class SchoolInformationAdapter(
    private val schoolsInfo: MutableList<ViewType> = mutableListOf(),
    private val onClickedSchool: (String) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //private val viewPool: RecyclerView.RecycledViewPool()


    fun updateSchools(newSchools: List<SchoolInfoResponse>){
        var tempChar = '+'
        val schoolListTemp: MutableList<SchoolInfoResponse> = mutableListOf()
        val schoolsDataAdapter: MutableList<SchoolsDataAdapter> = mutableListOf()
        var i = 0
        newSchools.sortedBy { it.schoolName }.forEach { school ->
            val firstLetter = school.schoolName?.first() ?: '+'
            if(firstLetter != tempChar){
                Log.d(TAG, "updateSchools: ${schoolListTemp.size}")
                if(schoolListTemp.size>0){
                    Log.d(TAG, "updateSchools - if: $tempChar")
                    val schoolsDataAdapterTemp = SchoolsDataAdapter(mutableListOf(), onClickedSchool)
                    schoolsDataAdapter.add(schoolsDataAdapterTemp)
                    schoolsDataAdapter[i].updateSchools(schoolListTemp)
                    schoolsInfo.add(ViewType.SCHOOLS_DATA(schoolsDataAdapter[i]))
                    i++
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

    override fun getItemViewType(position: Int): Int {
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
            layoutManager = GridLayoutManager(itemView.context,2)
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