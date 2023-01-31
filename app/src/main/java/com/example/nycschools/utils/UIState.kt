package com.example.nycschools.utils

import com.example.nycschools.model.DataType


/**
 * [Sealed class] - Defines the state of the UI
 */

sealed class UIState{

    object LOADING: UIState() //loading response
    data class SUCCESS(val response: List<DataType>): UIState() //response succeeded
    data class ERROR(val error: Exception): UIState() //error in response

}