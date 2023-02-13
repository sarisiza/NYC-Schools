package com.example.nycschools.database

import android.util.Log
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.utils.UIState
import javax.inject.Inject

private const val TAG = "LocalRepository"

interface LocalRepository {
    suspend fun getLocalSchools(): UIState<List<SchoolInfoResponse>>
    suspend fun insertSchools(schools: List<SchoolInfoResponse>)
    suspend fun getSchoolById(id: String): SchoolInfoResponse?
}

class LocalRepositoryImpl @Inject constructor(
    private val schoolsDAO: SchoolsDAO
) : LocalRepository {

    override suspend fun getLocalSchools(): UIState<List<SchoolInfoResponse>> {
        return try {
            val response = schoolsDAO.getSchools()
            UIState.SUCCESS(response)
        } catch (e: Exception) {
            UIState.ERROR(e)
        }
    }

    override suspend fun insertSchools(schools: List<SchoolInfoResponse>) {
        try {
            schoolsDAO.insertSchool(schools)
        } catch (e: Exception) {
            Log.e(TAG, "insertSchools: ${e.localizedMessage}", e)
        }
    }

    override suspend fun getSchoolById(id: String): SchoolInfoResponse? {
        return try {
            schoolsDAO.getSchoolById(id)
        } catch (e: Exception) {
            Log.e(TAG, "getSchoolById: ${e.localizedMessage}", e)
            null
        }
    }

}