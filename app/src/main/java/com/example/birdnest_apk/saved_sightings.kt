package com.example.birdnest_apk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.birdnest_apk.databinding.ActivitySavedSightingsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class saved_sightings : AppCompatActivity() {

    private lateinit var binding: ActivitySavedSightingsBinding
    private lateinit var database: DatabaseReference
    private lateinit var sightingRecycler : RecyclerView
    private lateinit var sightingArrayList : ArrayList<savedSightings>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySavedSightingsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnSearch.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnSight.setOnClickListener {
            val intent = Intent(this, Sightings::class.java)
            startActivity(intent)
        }

        binding.btnSave.setOnClickListener {

            val locationName = binding.txtLocation.text.toString()
            val city = binding.txtCity.text.toString()

            database = FirebaseDatabase.getInstance().getReference("savedSightings")
            val savedSightings = savedSightings(locationName, city)
            database.child(city).setValue(savedSightings).addOnSuccessListener {

                binding.txtLocation.text.clear()
                binding.txtCity.text.clear()
                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }

        }

        sightingRecycler = findViewById(R.id.sightingList)
        sightingRecycler.layoutManager = LinearLayoutManager(this)
        sightingRecycler.setHasFixedSize(true)

        sightingArrayList = arrayListOf<savedSightings>()
        getsavedSightingsData()
    }

    private fun getsavedSightingsData() {
        database = FirebaseDatabase.getInstance().getReference("savedSightings")

        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (savedSightingsSnapshot in snapshot.children) {

                        val savedSightings =
                            savedSightingsSnapshot.getValue(savedSightings::class.java)
                        sightingArrayList.add(savedSightings!!)
                    }
                    sightingRecycler.adapter = sightingAdapter(sightingArrayList)

                }
            }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

        })


    }
}