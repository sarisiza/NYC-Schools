package com.example.nycschools.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nycschools.model.SchoolInfoResponse

/**
 * [Interface] - Defines the Data Access Objects
 */

interface SchoolsDAO {

    @Query("SELECT * FROM schools")
    suspend fun getSchools(): List<SchoolInfoResponse>

    @Query("SELECT * FROM schools WHERE name LIKE: schoolName LIMIT 1")
    suspend fun getSchoolByName(schoolName: String): SchoolInfoResponse

    @Insert(
        entity = SchoolInfoResponse::class,
        onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(vararg people: SchoolInfoResponse)

}