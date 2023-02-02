package com.example.nycschools.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nycschools.model.SchoolItem

@Database(
    entities = [SchoolItem::class],
    version = 1
)

abstract class SchoolsDatabase: RoomDatabase() {
    abstract fun getSchoolsDAO(): SchoolsDAO
}