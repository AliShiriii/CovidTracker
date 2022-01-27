package com.example.covidtracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.covidtracker.R
import com.example.covidtracker.databinding.FragmentCovidTrackerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CovidTrackerFragment : Fragment() {

    private var _binding: FragmentCovidTrackerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCovidTrackerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}