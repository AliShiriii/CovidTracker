package com.example.covidtracker.api

import com.example.covidtracker.model.ModelClass
import retrofit2.Call
import retrofit2.http.GET

interface CovidApi {

    @GET("countries")
   suspend fun getCountryData(): Call<List<ModelClass>>

}