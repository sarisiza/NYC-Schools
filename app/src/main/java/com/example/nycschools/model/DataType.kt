package com.example.nycschools.model

sealed class DataType{

    data class SCHOOLS(val schoolItem: SchoolItem): DataType() //schools item
    data class SAT_RESULTS(val satResultsItem: SatResultsItem): DataType() //sat results item

}
