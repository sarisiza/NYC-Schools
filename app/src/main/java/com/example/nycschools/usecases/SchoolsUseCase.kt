package com.example.nycschools.usecases

import com.example.nycschools.database.LocalRepository
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.rest.NetworkState
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

//interface UseCase {
//    suspend fun <T> execute(): Flow<UIState<T>>
//}

class SchoolsUseCase @Inject constructor(
    private val repository: SchoolsRepository,
    private val localRepository: LocalRepository,
    private val networkState: NetworkState
) {

//    operator fun invoke(): Flow<UIState<List<SchoolInfoResponse>>> =
//        repository.getAllSchools()

    fun getSchools(): Flow<UIState<List<SchoolInfoResponse>>> {
        return if (networkState.isInternetOn()) {
            repository.getAllSchools()
        } else {
            flow { localRepository.getLocalSchools() }
        }
    }


//    override suspend fun <T> execute(): Flow<UIState<T>> {
//        TODO("Not yet implemented")
//    }

}