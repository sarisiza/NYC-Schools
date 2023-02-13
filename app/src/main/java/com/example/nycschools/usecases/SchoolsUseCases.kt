package com.example.nycschools.usecases

import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.utils.UIState
import kotlinx.coroutines.flow.Flow

/*interface UseCase {
    suspend fun <T> execute(): Flow<T>
}*/

class SchoolsUseCases(
    private val repository: SchoolsRepository
) {

    //better way to do it
//    operator fun invoke(): Flow<UIState<List<SchoolInfoResponse>>> =
//        repository.getAllSchools()

    //the one I liked better
//    override suspend fun <T> execute(): Flow<T> {
//
//    }

    //other way to do it
    val schools: Flow<UIState<List<SchoolInfoResponse>>> get() =
        repository.getAllSchools()

}