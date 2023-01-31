package com.example.nycschools.rest

import com.example.nycschools.model.SatResultsItem
import com.example.nycschools.model.SchoolItem
import retrofit2.Response
import retrofit2.http.GET

/**
 * [FunctionalInterface] - Defines the methods to connect to the schools API through retrofit
 */

interface SchoolsAPI {

    /**
     * Method to get schools list from API
     */
    @GET(SCHOOLS)
    suspend fun getSchools(): Response<List<SchoolItem>>

    /**
     * Method tho get SAT Results from API
     */
    @GET(SAT_RESULTS)
    suspend fun getSatResults(): Response<List<SatResultsItem>>

    /**
     * Object that defines the URL for the API
     */
    companion object{
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        private const val SCHOOLS = "s3k6-pzi2.json"
        private const val SAT_RESULTS = "f9bf-2cp4.json"
    }

}