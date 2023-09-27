package com.d121201068.gps

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private lateinit var locationManager: LocationManager
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

            // Minta izin lokasi
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 100)
                return@setOnClickListener
            }

            // Dapatkan lokasi GPS
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            // Tampilkan lokasi di layar
            textView.text = "Lokasi: ${location?.latitude}, ${location?.longitude}"
        }
    }
}