package com.example.birdnest_apk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.birdnest_apk.databinding.ActivitySightingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Sightings : AppCompatActivity() {

    private lateinit var binding: ActivitySightingsBinding
    val metric = true.toString()
    val apikey2 = "75unmc3na5uv"
    val hotspotId = "true"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivitySightingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.txtSaved.setOnClickListener {
            val intent = Intent(this, saved_sightings::class.java)
            startActivity(intent)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data2 = api.ebirdRetrofit.getBirdInfo("-25.7459277", "28.1879101", apikey2, hotspotId)

            if (data2.isSuccessful) {
                val hotspots = data2.body() // Assuming this is a List<Hotspot>

                // Extracting location names from the list
                val locationNames = hotspots?.map { it.locName } ?: emptyList()

                // Update the UI on the Main thread
                launch(Dispatchers.Main) {
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@Sightings)
                    recyclerView.adapter = LocationAdapter(locationNames)
                }
            } else {
                launch(Dispatchers.Main) {
                    Toast.makeText(this@Sightings, data2.message()!!, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}