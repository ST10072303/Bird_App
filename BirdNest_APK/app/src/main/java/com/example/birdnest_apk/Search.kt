package com.example.birdnest_apk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.birdnest_apk.databinding.ActivitySearchBinding
import com.example.birdnest_apk.databinding.ActivitySightingsBinding


class Search : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnSight.setOnClickListener {
            val intent = Intent(this, Sightings::class.java)
            startActivity(intent)
        }
    }
}