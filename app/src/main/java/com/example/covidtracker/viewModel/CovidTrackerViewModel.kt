package com.example.covidtracker.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidtracker.model.ModelClass
import com.example.covidtracker.repository.CovidTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CovidTrackerViewModel @Inject constructor(private val covidTrackerRepository: CovidTrackerRepository) :
    ViewModel() {

    private val _covid = MutableLiveData<List<ModelClass>>()
    val covid: LiveData<List<ModelClass>>
        get() = _covid

    init {

        getCountryData()

    }

    fun getCountryData() = viewModelScope.launch {

        val response = covidTrackerRepository.getCountryData()

        response.enqueue(object : Callback<List<ModelClass>>{
            override fun onResponse(
                call: Call<List<ModelClass>>,
                response: Response<List<ModelClass>>
            ) {

                _covid.value = response.body()
            }

            override fun onFailure(call: Call<List<ModelClass>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })

    }
}