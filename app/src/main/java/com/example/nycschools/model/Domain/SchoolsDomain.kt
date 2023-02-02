package com.example.nycschools.model.Domain


/**
 * Data class for School information
 */

data class SchoolsDomain(
    val dbn: String,
    val latitude: String,
    val location: String,
    val longitude: String,
    val overviewParagraph: String,
    val phoneNumber: String,
    val schoolEmail: String,
    val schoolName: String,
    val website: String
)
