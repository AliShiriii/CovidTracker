package com.example.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.covidtracker.databinding.ActivityMainBinding
import com.example.covidtracker.model.ModelClass
import com.hbb20.CountryCodePicker
import dagger.hilt.android.AndroidEntryPoint
import org.eazegraph.lib.charts.PieChart

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}