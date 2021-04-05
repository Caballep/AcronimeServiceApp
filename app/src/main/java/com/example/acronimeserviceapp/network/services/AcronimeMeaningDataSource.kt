package com.example.acronimeserviceapp.network.services

import javax.inject.Inject

class AcronimeMeaningDataSource @Inject constructor(
    private val acronimeMeaningService: AcronimeMeaningService
): BaseDataSource() {
    suspend fun getAcronimeMeaning(sf: String) = getResult { acronimeMeaningService.getAcronime(sf) }
}