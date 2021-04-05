package com.example.acronimeserviceapp.di

import com.example.acronimeserviceapp.network.services.AcronimeMeaningService
import com.example.acronimeserviceapp.network.services.AcronimeMeaningDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://www.nactem.ac.uk/software/acromine/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideAcronimeMeaningService(retrofit: Retrofit): AcronimeMeaningService = retrofit.create(AcronimeMeaningService::class.java)

    @Singleton
    @Provides
    fun provideAcronimeMeaningDataSource(characterService: AcronimeMeaningService) = AcronimeMeaningDataSource(characterService)

}