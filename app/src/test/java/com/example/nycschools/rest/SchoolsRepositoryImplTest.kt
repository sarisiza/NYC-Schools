package com.example.nycschools.rest

import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.utils.FailureResponseException
import com.example.nycschools.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class SchoolsRepositoryImplTest {

    private lateinit var testObject: SchoolsRepository
    private val mockServiceApi  = mockk<SchoolsAPI>(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        testObject = SchoolsRepositoryImpl(mockServiceApi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `get all schools when server is a success response returns a list of school is a success state`() {
        // AAA
        // ASSIGNMENT
        coEvery { mockServiceApi.getSchools() } returns mockk {
            every { isSuccessful } returns true
            every { body() }  returns listOf(
                mockk {
                    every { dbn } returns "123"
                      }
                ,
                mockk()
            )
        }
        val states = mutableListOf<UIState<List<SchoolInfoResponse>>>()

        // ACTION
        val job = testScope.launch {
            testObject.getAllSchools().collect {
                states.add(it)
            }
        }

        // ASSERTIONS
        assertEquals(2, states.size)
        assertEquals(2, (states[1] as UIState.SUCCESS).response.size)
        assertEquals("123", (states[1] as UIState.SUCCESS).response[0].dbn)

        job.cancel()
    }

    @Test(expected = FailureResponseException::class)
    fun `get all schools when server is a failure response returns  a error state`() {
        // AAA
        // ASSIGNMENT
        coEvery { mockServiceApi.getSchools() } returns mockk {
            every { isSuccessful } returns false
            every { errorBody() } returns mockk {
                every { string() } returns "ERROR"
            }
        }
        val states = mutableListOf<UIState<List<SchoolInfoResponse>>>()

        // ACTION
        val job = testScope.launch {
            testObject.getAllSchools().collect {
                states.add(it)
            }
        }

        // ASSERTIONS
        assertEquals(2, states.size)
        assertEquals("ERROR", (states[1] as UIState.ERROR).error.localizedMessage)

        job.cancel()
    }

    @Test(expected = HttpException::class)
    fun `get all schools when server throws an exception returns a error state`() {
        // AAA
        // ASSIGNMENT
        coEvery { mockServiceApi.getSchools() } throws HttpException(mockk(relaxed = true) {
            every { message() } returns "ERROR"
        })
        val states = mutableListOf<UIState<List<SchoolInfoResponse>>>()

        // ACTION
        val job = testScope.launch {
            testObject.getAllSchools().collect {
                states.add(it)
            }
        }

        // ASSERTIONS
        assertEquals(2, states.size)
        assertEquals("ERROR", (states[1] as UIState.ERROR).error.message)

        job.cancel()
    }
}