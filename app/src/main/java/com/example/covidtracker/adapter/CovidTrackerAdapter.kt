package com.example.covidtracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.databinding.CoronaItemBinding
import com.example.covidtracker.model.ModelClass
import java.text.NumberFormat

class CovidTrackerAdapter : RecyclerView.Adapter<CovidTrackerAdapter.CovidViewHolder>() {

    val numbers = 1

    inner class CovidViewHolder(private val binding: CoronaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(covidTracker: ModelClass) {

            when (numbers) {
                1 -> {

                    binding.countryCases.text =
                        NumberFormat.getInstance().format(covidTracker.cases)
                }
                2 -> {

                    binding.countryCases.text =
                        NumberFormat.getInstance().format(covidTracker.recovered)

                }
                3 -> {

                    binding.countryCases.text =
                        NumberFormat.getInstance().format(covidTracker.deaths)

                }
                else -> {

                    binding.countryCases.text =
                        NumberFormat.getInstance().format(covidTracker.active)

                }
            }

            binding.countryName.text = covidTracker.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {

        return CovidViewHolder(
            CoronaItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    private val diffCallBack = object : DiffUtil.ItemCallback<ModelClass>() {
        override fun areItemsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean {
            return oldItem.cases == newItem.cases
        }

        override fun areContentsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean {

            return oldItem == newItem

        }

    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {

        val covidData = differ.currentList[position]

        if (covidData != null) {

            holder.bind(covidData)

        }
    }

    override fun getItemCount(): Int {

        return differ.currentList.size

    }


    fun filter(chartText: String) {


        when (chartText) {
            "cases" -> {

                numbers == 1

            }

            "recovered" -> {

                numbers == 2

            }

            "deaths" -> {

                numbers == 3

            }

            else -> {

                numbers == 4

            }
        }
    }
}