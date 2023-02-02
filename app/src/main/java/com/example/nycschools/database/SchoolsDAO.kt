package com.example.nycschools.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nycschools.model.SchoolItem

/**
 * [Interface] - Defines the Data Access Objects
 */

interface SchoolsDAO {

    @Query("SELECT * FROM schools")
    suspend fun getSchools(): List<SchoolItem>

    @Query("SELECT * FROM schools WHERE name LIKE: schoolName LIMIT 1")
    suspend fun getSchoolByName(schoolName: String): SchoolItem

    @Insert(
        entity = SchoolItem::class,
        onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(vararg people: SchoolItem)

}