package com.example.nycschools.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nycschools.model.SchoolInfoResponse

/**
 * [Interface] - Defines the Data Access Objects
 */

@Dao
interface SchoolsDAO {

    @Query("SELECT * FROM schools")
    suspend fun getSchools(): List<SchoolInfoResponse>

    @Query("SELECT * FROM schools WHERE dbn LIKE :id LIMIT 1")
    suspend fun getSchoolById(id: String): SchoolInfoResponse

    @Insert(
        entity = SchoolInfoResponse::class,
        onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(schools: List<SchoolInfoResponse>)

}