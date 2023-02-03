package com.example.nycschools.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.TAG
import com.example.nycschools.model.SatResultsResponse
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class SchoolsViewModel(
    private val schoolsRepository: SchoolsRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    //backing schools LiveData
    private val _schoolsInfo: MutableLiveData<UIState<List<SchoolInfoResponse>>> = MutableLiveData(UIState.LOADING)
    val schoolsInfo: LiveData<UIState<List<SchoolInfoResponse>>> get() = _schoolsInfo

    //backing sat results LiveData
    private val _satResults: MutableLiveData<UIState<List<SatResultsResponse>>> = MutableLiveData(UIState.LOADING)
    val satResults: LiveData<UIState<List<SatResultsResponse>>> get() = _satResults


    /**
     * init block
     * get data from the api when initializing the ViewModel
     */
    init {
        Log.d(TAG, "ViewModel:Init ViewModel ")
        getSchools()

    }


    /**
     * Method for getting the SAT Results List
     */
    fun getSatResults(dbn: String) {
        viewModelScope.launch(ioDispatcher) {
            schoolsRepository.getAllSatResults(dbn).collect {
                _satResults.postValue(it)
            }
        }
    }

    /**
     * Method for getting the Schools List
     */
    fun getSchools() {
        viewModelScope.launch(ioDispatcher) {
            schoolsRepository.getAllSchools().collect {
                _schoolsInfo.postValue(it)
            }
        }
    }


}