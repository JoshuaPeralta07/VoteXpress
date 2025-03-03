package com.example.votexpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.votexpress.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val auckland = LatLng(-36.8485, 174.7633)
        val votingArea1 = LatLng(-36.8509, 174.7645)
        val votingArea2 = LatLng(-36.8525, 174.7683)
        val votingArea3 = LatLng(-36.8475, 174.7699)

        mMap.addMarker(MarkerOptions().position(votingArea1).title("Voting Area 1"))
        mMap.addMarker(MarkerOptions().position(votingArea2).title("Voting Area 2"))
        mMap.addMarker(MarkerOptions().position(votingArea3).title("Voting Area 3"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(auckland,12.0f))
    }
}