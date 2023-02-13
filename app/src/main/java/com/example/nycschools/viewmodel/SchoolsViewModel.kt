package com.example.nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.model.SatResultsResponse
import com.example.nycschools.model.SchoolInfoResponse
import com.example.nycschools.rest.SchoolsRepository
import com.example.nycschools.usecases.SchoolsUseCase
import com.example.nycschools.utils.UIState
import com.example.nycschools.utils.ViewIntent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class SchoolsViewModel(
    private val schoolsRepository: SchoolsRepository,
    private val schoolsUseCase: SchoolsUseCase,
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

    var schoolItem: SchoolInfoResponse? = null
    var satItem: SatResultsResponse? = null

    fun getSchoolItem(dbn: String, schoolList: List<SchoolInfoResponse>?){
        var found = false
        var i = 0
        while (!found && i < (schoolList?.size ?: 0)){
            if(schoolList?.get(i)?.dbn == dbn){
                schoolItem = schoolList[i]
                found = true
            }
            i++
        }
    }

    fun getSatItem(dbn: String, satList: List<SatResultsResponse>?){
        var found = false
        var i = 0
        while (!found && i < (satList?.size ?: 0)){
            if(satList?.get(i)?.dbn == dbn){
                satItem = satList[i]
                found = true
            }
            i++
        }
    }

    /**
     * init block
     * get data from the api when initializing the ViewModel
     */
    init {
//        getSchools()
//        getSatResults()
    }

    fun getIntentView(intentView: ViewIntent) {
        when(intentView) {
            is ViewIntent.SCHOOLS -> { getSchools() }
            is ViewIntent.SAT_SCORES -> { getSatResults() }
            is ViewIntent.SCHOOL_SCORE  -> {}
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
            schoolsUseCase.getSchools().collect {
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