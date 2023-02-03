package com.example.nycschools.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.TAG
import com.example.nycschools.model.SchoolItem
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.utils.NullSchoolsException
import com.example.nycschools.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SchoolsViewModel(
    private val schoolsRepository: SchoolsRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    //backing schools LiveData
    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    //backing sat results LiveData
    private val _satResults: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val satResults: LiveData<UIState> get() = _satResults

    //private val _schoolsData: MutableLiveData<UIState> = _schools.combineWith(_satResults){}

    /**
     * init block
     * get data from the api when initializing the ViewModel
     */
    init {
        Log.d(TAG, "ViewModel:Init ViewModel ")
        getSchools()
        getSatResults()
    }


    /**
     * Method for getting the SAT Results List
     */
    private fun getSatResults() {
        viewModelScope.launch(ioDispatcher) {
            schoolsRepository.getAllSatResults().collect {
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
                withContext(Dispatchers.Main){
                    Log.d(TAG, "getSchools: $it")
                }
            }
        }
    }




}