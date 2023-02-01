package com.example.nycschools.rest

import android.util.Log
import com.example.nycschools.TAG
import com.example.nycschools.model.DataType
import com.example.nycschools.utils.FailureResponseException
import com.example.nycschools.utils.NullSatResultsException
import com.example.nycschools.utils.NullSchoolsException
import com.example.nycschools.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * [FunctionalInterface] - Defines retrofit methods
 */
interface SchoolsRepository {

    /**
     * Method to get schools
     */
    fun getAllSchools(): Flow<UIState>

    /**
     * Method to get SAT results
     */
    fun getAllSatResults(): Flow<UIState>

}

/**
 * [Class] - Implement retrofit
 */

class SchoolsRepositoryImpl @Inject constructor(
    private val schoolsAPI: SchoolsAPI
) : SchoolsRepository{

    /**
     * Method to get schools
     */
    override fun getAllSchools(): Flow<UIState> = flow{
        emit(UIState.LOADING)
        try {
            val response = schoolsAPI.getSchools() //get json
            if(response.isSuccessful){ //check if response was successful
                response.body()?.let {
                    emit(UIState.SUCCESS(it as List<DataType>))
                }?: throw NullSchoolsException() //check if response was null
            }else throw FailureResponseException(response.errorBody()?.string())
        }catch (e: Exception){
            emit(UIState.ERROR(e))
            Log.e(TAG, "getAllSchools: ${e.localizedMessage}", e)
        }
    }

    /**
     * Method to get SAT results
     */
    override fun getAllSatResults(): Flow<UIState> = flow{
        emit(UIState.LOADING)
        try {
            val response = schoolsAPI.getSatResults() //get json
            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.SUCCESS(it as List<DataType>))
                }?: throw NullSatResultsException()
            } else throw FailureResponseException(response.errorBody()?.string())
        }catch (e: Exception){
            emit(UIState.ERROR(e))
            Log.e(TAG, "getAllSatResults: ${e.localizedMessage}", e)
        }
    }

}