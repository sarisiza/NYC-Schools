package com.example.nycschools.model.Domain


/**
 * Data class for SAT information
 */

data class SatResultsDomain(
    val dbn: String,
    val satCriticalReadingAvgScore: String,
    val satMathAvgScore: String,
    val satWritingAvgScore: String
)
