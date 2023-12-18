package com.example.retrofitforecaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.r_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WeatherAdapter()
        recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            val weatherData = RetrofitService.create().getWeatherForecast("Shklov", "2d8d698a221ec7b953940e9087afb302", "metric")
            withContext(Dispatchers.Main) {
                adapter.submitList(weatherData.list)
            }
        }
    }
}
