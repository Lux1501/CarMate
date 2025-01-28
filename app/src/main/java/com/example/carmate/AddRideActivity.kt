package com.example.carmate

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddRideActivity : AppCompatActivity() {

    // Lista za pohranu vožnji
    companion object {
        val rides = mutableListOf<Ride>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ride)

        val etStartLocation = findViewById<EditText>(R.id.et_start_location)
        val etEndLocation = findViewById<EditText>(R.id.et_end_location)
        val etPrice = findViewById<EditText>(R.id.et_price)
        val tvDepartureTime = findViewById<TextView>(R.id.tv_departure_time)
        val btnPickTime = findViewById<Button>(R.id.btn_pick_time)
        val btnAdd = findViewById<Button>(R.id.btn_add)

        btnPickTime.setOnClickListener {
            // Dobijemo trenutni čas i minute
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // Otvaranje TimePickerDialoga
            val timePickerDialog = TimePickerDialog(
                this,
                { _, selectedHour, selectedMinute ->
                    val timeString = String.format("%02d:%02d", selectedHour, selectedMinute)
                    tvDepartureTime.text = timeString
                },
                hour, minute, true
            )
            timePickerDialog.show()
        }

        btnAdd.setOnClickListener {
            val startLocation = etStartLocation.text.toString()
            val endLocation = etEndLocation.text.toString()
            val price = etPrice.text.toString()
            val departureTime = tvDepartureTime.text.toString()

            if (startLocation.isNotEmpty() && endLocation.isNotEmpty() && price.isNotEmpty() && departureTime != "Vrijeme polaska") {
                // Dodaj vožnju u listu
                val newRide = Ride(startLocation, endLocation, departureTime, price)
                rides.add(newRide)

                Toast.makeText(
                    this,
                    "Vožnja dodana: $startLocation do $endLocation, cijena: $price €, polazak: $departureTime",
                    Toast.LENGTH_SHORT
                ).show()

                // Očisti unos
                etStartLocation.text.clear()
                etEndLocation.text.clear()
                etPrice.text.clear()
                tvDepartureTime.text = "Vrijeme polaska"
            } else {
                Toast.makeText(this, "Molimo ispunite sva polja!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    data class Ride(
        val startLocation: String,
        val endLocation: String,
        val departureTime: String,
        val price: String
    )
}
