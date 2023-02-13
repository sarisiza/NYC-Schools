package com.example.nycschools.rest

import android.util.Log
import com.example.nycschools.TAG
import com.example.nycschools.model.SatResultsResponse
import com.example.nycschools.model.SchoolInfoResponse
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
    fun getAllSchools(): Flow<UIState<List<SchoolInfoResponse>>>

    /**
     * Method to get SAT results
     */
    fun getAllSatResults(): Flow<UIState<List<SatResultsResponse>>>

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
    override fun getAllSchools(): Flow<UIState<List<SchoolInfoResponse>>> = flow{
        emit(UIState.LOADING)
        try {
            val response = schoolsAPI.getSchools() //get json
            if(response.isSuccessful) { //check if response was successful
                response.body()?.let {
                    emit(UIState.SUCCESS(it))
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
    override fun getAllSatResults(): Flow<UIState<List<SatResultsResponse>>> = flow{
        emit(UIState.LOADING)
        try {
            val response = schoolsAPI.getSatResults() //get json
            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.SUCCESS(it))
                }?: throw NullSatResultsException()
            } else{
                throw FailureResponseException(response.errorBody()?.string())
            }
        }catch (e: Exception){
            emit(UIState.ERROR(e))
            Log.e(TAG, "getAllSatResults: ${e.localizedMessage}", e)
        }
    }

}