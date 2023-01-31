package com.example.nycschools.utils

/**
 * [Sealed class] - Defines the state of the UI
 */

sealed class UIState{

    object LOADING: UIState()
    //todo add success state after domain data created
    data class ERROR(val error: Exception): UIState()

}