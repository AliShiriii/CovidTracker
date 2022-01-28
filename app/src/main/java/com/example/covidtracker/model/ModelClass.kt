package com.example.covidtracker.model

import com.google.gson.annotations.SerializedName

data class ModelClass(

    @SerializedName("cases")
    val cases: String,
    @SerializedName("todayCases")
    val todayCases: String,
    @SerializedName("deaths")
    val deaths: String,
    @SerializedName("todayDeaths")
    val todayDeaths: String,
    @SerializedName("recovered")
    val recovered: String,
    @SerializedName("todayRecovered")
    val todayRecovered: String,
    @SerializedName("active")
    val active: String,
    @SerializedName("country")
    val country: String
)
