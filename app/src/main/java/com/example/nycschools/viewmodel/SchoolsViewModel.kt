package com.example.nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.model.SatResultsResponse
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.utils.UIState
import com.example.nycschools.utils.ViewIntent
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

    private val _itemSelected: MutableLiveData<String> = MutableLiveData("")
    val itemSelected: LiveData<String> get() = _itemSelected

    //backing schools LiveData
    private val _schoolsInfoList: MutableLiveData<List<SchoolInfoResponse>?> = MutableLiveData()
    val schoolsInfoList: LiveData<List<SchoolInfoResponse>?> get() = _schoolsInfoList

    //backing sat results LiveData
    private val _satResultsList: MutableLiveData<List<SatResultsResponse>?> = MutableLiveData()
    val satResultsList: LiveData<List<SatResultsResponse>?> get() = _satResultsList


    fun getIntentView(intentView: ViewIntent){
        when(intentView){
            ViewIntent.SAT_SCORES -> {
                getSchools()
            }
            ViewIntent.SCHOOLS -> {
                getSatResults()
            }
            is ViewIntent.SCHOOL_SCORE -> {}
        }
    }


    /**
     * Method for getting the SAT Results List
     */
    private fun getSatResults() {
        viewModelScope.launch(ioDispatcher) {
            schoolsRepository.getAllSatResults().collect {
                _satResults.postValue(it)
                if(it is UIState.SUCCESS<List<SatResultsResponse>>){
                    _satResultsList.postValue(it.response)
                }
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
                if(it is UIState.SUCCESS<List<SchoolInfoResponse>>){
                    _schoolsInfoList.postValue(it.response)
                }
            }
        }
    }

    fun selectItem(dbn: String){
        _itemSelected.value = dbn
    }


}