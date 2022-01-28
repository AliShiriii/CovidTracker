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
import javax.inject.Inject

@HiltViewModel
class CovidTrackerViewModel @Inject constructor(private val covidTrackerRepository: CovidTrackerRepository) :
    ViewModel() {

    private val _covid = MutableLiveData<Call<List<ModelClass>>>()
    val covid: LiveData<Call<List<ModelClass>>>
        get() = _covid

    fun getCountryData() = viewModelScope.launch {
       val response = covidTrackerRepository.getCountryData()

        _covid.value = response
    }
}