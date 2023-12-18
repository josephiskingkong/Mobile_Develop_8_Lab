package com.example.retrofitforecaster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter : ListAdapter<WeatherForecast, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherForecast>() {
            override fun areItemsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
                return oldItem.dt_txt == newItem.dt_txt
            }

            override fun areContentsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val temp = getItem(position).main.temp
        return if (temp < 0) R.layout.cold_layout else R.layout.hot_layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return if (viewType == R.layout.hot_layout) ViewHolderHot(view) else ViewHolderCold(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val forecast = getItem(position)
        if (holder is ViewHolderHot) {
            holder.bind(forecast)
        } else if (holder is ViewHolderCold) {
            holder.bind(forecast)
        }
    }
}