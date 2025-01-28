package com.example.carmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title = findViewById<TextView>(R.id.tv_title)
        title.text = "CarMate"

        val btnSearchRides = findViewById<Button>(R.id.btn_search_rides)
        val btnAddRide = findViewById<Button>(R.id.btn_add_ride)

        btnSearchRides.setOnClickListener {
            startActivity(Intent(this, SearchRidesActivity::class.java))
        }

        btnAddRide.setOnClickListener {
            startActivity(Intent(this, AddRideActivity::class.java))
        }
    }
}

