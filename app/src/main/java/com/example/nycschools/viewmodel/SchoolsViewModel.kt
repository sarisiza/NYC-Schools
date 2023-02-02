package com.example.nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.model.SchoolItem
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SchoolsViewModel(
    private val schoolsRepository: SchoolsRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    /**
     * init block
     * get data from the api when initializing the ViewModel
     */
    init {
        getSchools()
    }

    //backing schools LiveData
    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    //backing sat results LiveData
    private val _satResults: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val satResults: LiveData<UIState> get() = _satResults

    /**
     * Method for getting the SAT Results List
     */
    private fun getSatResults(dbn: String) {
        viewModelScope.launch(ioDispatcher) {
            schoolsRepository.getAllSatResults(dbn).collect {
                _satResults.postValue(it)
            }
        }
    }

    /**
     * Method for getting the Schools List
     */
    private fun getSchools() {
        viewModelScope.launch(ioDispatcher) {
            schoolsRepository.getAllSchools().collect {
                _schools.postValue(it)
            }
        }
    }

    fun getSatResultByID(schoolItem: SchoolItem){
        schoolItem.dbn?.let { getSatResults(it) }
    }


}