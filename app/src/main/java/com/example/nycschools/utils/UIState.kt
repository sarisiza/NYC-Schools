package com.example.nycschools.utils


/**
 * [Sealed class] - Defines the state of the UI
 */

sealed class UIState<out T>{

    object LOADING: UIState<Nothing>() //loading response
    data class SUCCESS<T>(val response: T): UIState<T>() //response succeeded
    data class ERROR(val error: Exception): UIState<Nothing>() //error in response

}