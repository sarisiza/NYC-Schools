package com.example.nycschools.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.usecases.SchoolsUseCase
import com.example.nycschools.viewmodel.SchoolsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SchoolsViewModelFactory @Inject constructor(
    private val schoolsRepository: SchoolsRepository,
    private val schoolsUseCase: SchoolsUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SchoolsViewModel(
            schoolsRepository,
            schoolsUseCase,
            ioDispatcher
        ) as T
    }

}