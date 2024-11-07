package com.example.birdnest_apk

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.birdnest_apk.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtSignout.setOnClickListener {
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
        }

        binding.btnSearch.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

        binding.btnSight.setOnClickListener {
            val intent = Intent(this, Sightings::class.java)
            startActivity(intent)
        }

        binding.imageFace.setOnClickListener {
            openUrl("https://www.facebook.com/YourProfile/")
        }

        binding.imageInsta.setOnClickListener {
            openUrl("https://www.instagram.com/")
        }

        binding.imageX.setOnClickListener {
            openUrl("https://twitter.com/login")
        }

        binding.imageTube.setOnClickListener {
            openUrl("https://www.youtube.com/user/YouTube")
        }


        val media1 = MediaPlayer.create(this, R.raw.bird2)
        val media2 = MediaPlayer.create(this, R.raw.bird3)
        val media3 = MediaPlayer.create(this, R.raw.bird6)


        binding.txtB1.setOnClickListener{

            media1.start()
        }
        binding.txtB2.setOnClickListener{

            media2.start()
        }
        binding.txtB3.setOnClickListener{

            media3.start()
        }
    }

    private fun openUrl(link: String) {

        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        startActivity(intent)
    }
}

