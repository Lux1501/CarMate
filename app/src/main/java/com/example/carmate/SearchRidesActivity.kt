package com.example.carmate

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchRidesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_rides)

        val etStartLocation = findViewById<EditText>(R.id.et_start_location)
        val etEndLocation = findViewById<EditText>(R.id.et_end_location)
        val btnSearch = findViewById<Button>(R.id.btn_search)
        val tvResults = findViewById<TextView>(R.id.tv_results)

        btnSearch.setOnClickListener {
            val startLocation = etStartLocation.text.toString().trim()
            val endLocation = etEndLocation.text.toString().trim()

            // Filtriranje vožnji na temelju unesenih lokacija
            val filteredRides = AddRideActivity.rides.filter {
                it.startLocation.contains(startLocation, ignoreCase = true) &&
                        it.endLocation.contains(endLocation, ignoreCase = true)
            }

            // Prikaz rezultata
            if (filteredRides.isNotEmpty()) {
                val results = filteredRides.joinToString(separator = "\n") {
                    "Vožnja: ${it.startLocation} -> ${it.endLocation}, Vrijeme: ${it.departureTime}, Cijena: ${it.price} €"
                }
                tvResults.text = results
            } else {
                tvResults.text = "Nema pronađenih vožnji za zadane kriterije."
            }
        }
    }
}
