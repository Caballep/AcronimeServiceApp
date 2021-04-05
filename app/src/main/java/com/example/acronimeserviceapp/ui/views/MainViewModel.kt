package com.example.acronimeserviceapp.ui.views

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronimeserviceapp.network.entities.AcronimeMeaningResponse
import com.example.acronimeserviceapp.network.services.ServiceResult
import com.example.acronimeserviceapp.repository.AcronimeMeaningRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel @ViewModelInject constructor(
    private val acronimeMeaningMeaningRepository: AcronimeMeaningRepository
): ViewModel() {

    private val acronimeMeaningMutableLiveData = MutableLiveData<ServiceResult<List<AcronimeMeaningResponse>>>()
    val acronimeMeaningLiveData: LiveData<ServiceResult<List<AcronimeMeaningResponse>>>
        get() = acronimeMeaningMutableLiveData

    fun fetchAcronimeMeaning(sf: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                acronimeMeaningMutableLiveData.postValue(acronimeMeaningMeaningRepository.getAcronimeMeaning(sf))
            }
        }
    }
}