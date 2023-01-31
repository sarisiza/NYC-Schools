package com.example.nycschools.rest

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * [Object] - access the network service
 * Singleton - only one instance will be created
 */

object Network {

    /**
     * Creating retrofit object using Builder
     */
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(SchoolsAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    /**
     * Creating a [SchoolsAPI]
     */
    val schoolsAPI by lazy {
        retrofit.create(SchoolsAPI::class.java)
    }


}