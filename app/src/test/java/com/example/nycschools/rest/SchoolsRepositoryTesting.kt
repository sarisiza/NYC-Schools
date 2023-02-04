package com.example.nycschools.rest

import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.rest.SchoolsRepositoryImpl
import com.example.nycschools.utils.UIState
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Type

class SchoolsRepositoryTesting {

    private lateinit var testObject: SchoolsRepository //interface

    private val mockSchoolsAPI: SchoolsAPI = mockk(relaxed = true)


    @Before
    fun setUp() {
        testObject = SchoolsRepositoryImpl(mockSchoolsAPI) //implementation
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `get all schools when server provides a list of schools should return a success state`(){
        runTest {
            //ASSIGNMENT
            coEvery { mockSchoolsAPI.getSchools() } returns mockk {
                every { isSuccessful } returns true
                every { body() } returns listOf(SchoolInfoResponse(), SchoolInfoResponse())
            }

            val states = mutableListOf<UIState<T>>()

            //ACTION
            testObject.getAllSchools().collect {
                states.add(it)
            }


            //ASSERTION
            assertThat(states).hasSize(2)
            assertThat(states.first()).isInstanceOf(UIState.LOADING::class.java)
            assertThat(states[1]).isInstanceOf(UIState.SUCCESS::class.java)
            assertThat(
                (states[1] as UIState.SUCCESS<List<SchoolInfoResponse>>).response
            ).hasSize(2)
        }
    }

}

