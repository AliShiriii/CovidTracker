package com.example.covidtracker.repository

import com.example.covidtracker.api.CovidApi
import javax.inject.Inject

class CovidTrackerRepository @Inject constructor(private val covidApi: CovidApi){


}