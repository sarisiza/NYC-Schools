package com.example.nycschools.rest

import android.util.Log
import com.example.nycschools.TAG
import com.example.nycschools.database.LocalRepository
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
    private val schoolsAPI: SchoolsAPI,
    private val localRepository: LocalRepository
) : SchoolsRepository{

    /**
     * Method to get schools
     */
    override fun getAllSchools(): Flow<UIState<List<SchoolInfoResponse>>> = flow{
        emit(UIState.LOADING)
        try {
            if (localRepository.getLocalSchools() is UIState.SUCCESS  &&
                (localRepository.getLocalSchools() as UIState.SUCCESS).response.isNotEmpty() ) {
                Log.d(TAG, "getAllSchools: You got data from database without calling server")
                emit(UIState.SUCCESS(
                    (localRepository.getLocalSchools() as UIState.SUCCESS<List<SchoolInfoResponse>>).response
                )
                )
            } else {
                val response = schoolsAPI.getSchools() //get json
                if(response.isSuccessful) { //check if response was successful
                    response.body()?.let {
                        localRepository.insertSchools(it)
                        if (localRepository.getLocalSchools() is UIState.SUCCESS) {
                            Log.d(TAG, "getAllSchools: You got data from database")
                            emit(UIState.SUCCESS(
                                (localRepository.getLocalSchools() as UIState.SUCCESS<List<SchoolInfoResponse>>).response
                            )
                            )
                        } else {
                            Log.d(TAG, "getAllSchools: YOU GOT DATA FROM NETWORK")
                            emit(UIState.SUCCESS(it))
                        }
                    }?: throw NullSchoolsException() //check if response was null
                }else throw FailureResponseException(response.errorBody()?.string())
            }
        }catch (e: Exception){
            emit(localRepository.getLocalSchools())
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