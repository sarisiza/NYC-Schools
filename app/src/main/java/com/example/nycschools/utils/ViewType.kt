package com.example.nycschools.utils

import com.example.nycschools.view.adapter.SchoolsDataAdapter

sealed class ViewType{

    data class LETTER(val letter: String): ViewType()
    data class SCHOOLS_DATA(val schoolsData: SchoolsDataAdapter): ViewType()

}
