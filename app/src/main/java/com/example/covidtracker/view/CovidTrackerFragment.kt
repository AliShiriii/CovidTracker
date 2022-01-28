package com.example.covidtracker.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.adapter.CovidTrackerAdapter
import com.example.covidtracker.databinding.FragmentCovidTrackerBinding
import com.example.covidtracker.model.ModelClass
import com.example.covidtracker.viewModel.CovidTrackerViewModel
import com.hbb20.CountryCodePicker
import dagger.hilt.android.AndroidEntryPoint
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

@AndroidEntryPoint
class CovidTrackerFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentCovidTrackerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CovidTrackerViewModel by viewModels()

    var active: Int = 0
    var total: Int = 0
    var recovered: Int = 0
    var deaths: Int = 0

    private lateinit var countryCodePicker: CountryCodePicker
    private lateinit var spinner: Spinner
    private lateinit var country: String
    private lateinit var pieChart: PieChart
    private lateinit var covidTrackerAdapter: CovidTrackerAdapter
    var types = arrayOf("cases", "deaths", "recovered", "active")
    private lateinit var modelClassList1: ArrayList<ModelClass>
    private lateinit var modelClassList2: ArrayList<ModelClass>

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

        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, types)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.setSelection(0, true)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                val item = types[position]
                binding.filter.text = item
                covidTrackerAdapter.filter(item)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }

        viewModel.getCountryData()

        viewModel.covid.observe(viewLifecycleOwner, { countryData ->

            countryData.execute().body()?.let { modelClassList2.addAll(it) }

            covidTrackerAdapter.notifyDataSetChanged()

        })

        covidTrackerAdapter = CovidTrackerAdapter()
        covidTrackerAdapter.differ.submitList(modelClassList2)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = covidTrackerAdapter

        countryCodePicker.setAutoDetectedCountry(true)
        country = countryCodePicker.selectedCountryName

        countryCodePicker.setOnCountryChangeListener {

            countryCodePicker.selectedCountryName
            fetchData()
        }

        fetchData()
    }


    private fun fetchData() {

        viewModel.getCountryData()
        viewModel.covid.observe(viewLifecycleOwner, { covidData ->

            covidData.execute().body()?.let { modelClassList1.addAll(it) }


            for (covid in 0 until modelClassList1.size) {

                if (modelClassList1[covid].country == country) {

                    binding.activeCase.text =
                        (modelClassList1[covid].active)
                    binding.todayDeaths.text =
                        (modelClassList1[covid].todayDeaths)
                    binding.todayRecovered.text =
                        (modelClassList1[covid].todayRecovered)
                    binding.todayTotal.text =
                        (modelClassList1[covid].todayCases)
                    binding.totalCases.text =
                        (modelClassList1[covid].cases)
                    binding.DeathCases.text =
                        (modelClassList1[covid].deaths)
                    binding.recoveredCase.text =
                        (modelClassList1[covid].recovered)

                    active = Integer.parseInt(modelClassList1[covid].active)
                    total = Integer.parseInt(modelClassList1[covid].cases)
                    recovered = Integer.parseInt(modelClassList1[covid].recovered)
                    deaths = Integer.parseInt(modelClassList1[covid].deaths)

                    updateGraph(active, total, recovered, deaths)
                }
            }
        })

    }

    private fun updateGraph(active: Int, total: Int, recovered: Int, deaths: Int) {

        pieChart.clearChart()
        pieChart.addPieSlice(PieModel("Confirm", total.toFloat(), Color.parseColor("#FFB701")))
        pieChart.addPieSlice(PieModel("Active", active.toFloat(), Color.parseColor("#FF44caf50")))
        pieChart.addPieSlice(PieModel("Recovered", recovered.toFloat(), Color.parseColor("#38ACCD")))
        pieChart.addPieSlice(PieModel("Deaths", deaths.toFloat(), Color.parseColor("#F55c48")))
        pieChart.startAnimation()

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}