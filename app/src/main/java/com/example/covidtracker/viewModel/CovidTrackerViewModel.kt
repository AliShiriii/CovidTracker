package com.example.covidtracker.viewModel

import androidx.lifecycle.ViewModel
import com.example.covidtracker.repository.CovidTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CovidTrackerViewModel @Inject constructor(private val covidTrackerRepository: CovidTrackerRepository) :
    ViewModel() {

}