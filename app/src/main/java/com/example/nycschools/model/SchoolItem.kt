package com.example.nycschools.model


import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * [Data Class] - Defines the properties of a school
 */


@JsonClass(generateAdapter = true)
data class SchoolItem(
    //Schools Item
    @Json(name = "dbn")
    val dbn: String? = null,
    @Json(name = "latitude")
    val latitude: String? = null,
    @Json(name = "longitude")
    val longitude: String? = null,
    @Json(name = "overview_paragraph")
    val overviewParagraph: String? = null,
    @Json(name = "school_name")
    val schoolName: String? = null,

    //SAT Results Item
    @Json(name = "sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String?,
    @Json(name = "sat_math_avg_score")
    val satMathAvgScore: String?,
    @Json(name = "sat_writing_avg_score")
    val satWritingAvgScore: String?
)