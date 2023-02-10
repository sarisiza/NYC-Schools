package com.example.nycschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.isMockKMock
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SchoolsViewModelTest {

    private lateinit var testObject: SchoolsViewModel

    private val mockRepository = mockk<SchoolsRepository>(relaxed = true)
    private val mockDispatcher = UnconfinedTestDispatcher()

    @get:Rule val instantTask =  InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(mockDispatcher)
        testObject = SchoolsViewModel(mockRepository, mockDispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `get schools when repository retrieves a list of school response  returns success state`() {
        //  AAA
        // ASSIGNMENT
        every { mockRepository.getAllSchools() } returns flowOf(
            UIState.SUCCESS(listOf(mockk(), mockk(), mockk()))
        )
        testObject.schoolsInfo.observeForever {
            when(it)  {
                is UIState.LOADING -> {

                }
                is UIState.SUCCESS -> {
                    assertEquals(3, it.response.size)
                }
                is UIState.ERROR -> {

                }
            }
        }
        val list =  mutableListOf<SchoolInfoResponse>()
        testObject.schoolsInfoList.observeForever {
            list.addAll(it!!)
        }

        // ACTION
        testObject.getSchools()

        // ASSERT
        assertEquals(3, list.size)
    }
}