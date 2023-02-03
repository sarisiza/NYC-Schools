package com.example.nycschools.model


import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * [Data Class] - Defines the properties of a school
 */

@Entity("schools")
@JsonClass(generateAdapter = true)
data class SatResultsResponse(
    @Json(name = "dbn")
    val dbn: String? = null,
    @Json(name = "sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String? = null,
    @Json(name = "sat_math_avg_score")
    val satMathAvgScore: String? = null,
    @Json(name = "sat_writing_avg_score")
    val satWritingAvgScore: String? = null
)