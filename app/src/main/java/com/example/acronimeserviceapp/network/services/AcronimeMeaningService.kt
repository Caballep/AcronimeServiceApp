package com.example.acronimeserviceapp.network.services

import com.example.acronimeserviceapp.network.entities.AcronimeMeaningResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronimeMeaningService {
    @GET("dictionary.py/{sf}")
    suspend fun getAcronime(@Query("sf") sf: String): Response<List<AcronimeMeaningResponse>>
}