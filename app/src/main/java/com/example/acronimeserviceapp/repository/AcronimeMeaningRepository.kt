package com.example.acronimeserviceapp.repository

import com.example.acronimeserviceapp.network.entities.AcronimeMeaningResponse
import com.example.acronimeserviceapp.network.services.AcronimeMeaningDataSource
import com.example.acronimeserviceapp.network.services.ServiceResult
import javax.inject.Inject

class AcronimeMeaningRepository @Inject constructor (
    private val acronimeMeaningDataSource: AcronimeMeaningDataSource
) {
    suspend fun getAcronimeMeaning(sf: String): ServiceResult<List<AcronimeMeaningResponse>> {
        return acronimeMeaningDataSource.getAcronimeMeaning(sf)
    }
}