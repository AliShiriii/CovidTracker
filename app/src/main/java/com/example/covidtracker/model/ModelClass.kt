package com.example.covidtracker.model

data class ModelClass(
    val cases: String,
    val todayCases: String,
    val deaths: String,
    val todayDeaths: String,
    val recovered: String,
    val todayRecovered: String,
    val active: String,
    val country: String
)
