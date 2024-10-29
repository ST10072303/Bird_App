package com.example.birdnest_apk

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.ViewModelProvider
import com.example.birdnest_apk.api.ebirdRetrofit
import com.example.birdnest_apk.databinding.ActivitySearchBinding
import com.example.birdnest_apk.models.ebird.ObservationsItem
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.common.MapboxOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.locationcomponent.location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context



class Search : AppCompatActivity(),PermissionsListener{
    lateinit var mapView : MapView
    lateinit var permissionsManager: PermissionsManager
    private lateinit var pointAnnotationManager:PointAnnotationManager

    //lateinit var birds:birds

    private lateinit var binding: ActivitySearchBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapboxOptions.accessToken = "pk.eyJ1Ijoic3QxMDEwMzcwNCIsImEiOiJjbTE5YWJ1cjcwM3UyMmpyMTU4dXoxb2IxIn0.3SGuVJBtoxgfraWYUUqrtQ"
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //birds = ViewModelProvider(this).get(birds::class.java)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnSight.setOnClickListener {
            val intent = Intent(this, Sightings::class.java)
            startActivity(intent)
        }

        // Create a map programmatically and set the initial camera
        mapView =findViewById(R.id.mapView)



            if (PermissionsManager.areLocationPermissionsGranted(this)) {
                // Permission sensitive logic called here, such as activating the Maps SDK's LocationComponent to show the device's location
                mapView.mapboxMap.setCamera(
                    CameraOptions.Builder()
                        .center(Point.fromLngLat(28.218, -25.732))
                        .pitch(0.0)
                        .zoom(12.0)
                        .bearing(180.0)
                        .build()
                )

                mapView.location.enabled = true


                CoroutineScope(Dispatchers.IO).launch {
                    val data = ebirdRetrofit.getBirdInfo("-25.732", "28.218","75unmc3na5uv", "true")

                    Log.v("appDebug",data.raw().toString())

                        if (data.isSuccessful)


                        {     val locationList = data.body()

                            launch(Dispatchers.Main) {
                                pointAnnotationManager=mapView.annotations.createPointAnnotationManager()
                                locationList?.forEach{obse->


                                    val pointAnnotationOptions = PointAnnotationOptions()
                                        .withPoint(Point.fromLngLat(obse.lng,obse.lat))

                                        .withIconImage(getDrawable(R.drawable.red_marker)!!.toBitmap())

                                    pointAnnotationManager.create(pointAnnotationOptions)

                                }

                            }
                        }

                    else

                        {

                            Log.v("appDebug","Something went wrong")
                        }






                }


            } else {
                permissionsManager = PermissionsManager(this)
                permissionsManager.requestLocationPermissions(this)
            }



       /* mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS){
            pointAnnotationManager=mapView.annotations.createPointAnnotationManager()

        }*/
        // Add the map view to the activity (you can also add it to other views as a child)


        Log.v("appdebug",mapView.mapboxMap.cameraState.zoom.toString())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onExplanationNeeded(permissionsToExplain: List<String>) {

    }

    override fun onPermissionResult(granted: Boolean) {

    }
}
   /* private fun bird(observation: List<ObservationsItem>){
        observation.forEach{obse->


            val pointAnnotationOptions = PointAnnotationOptions()
                .withPoint(Point.fromLngLat(obse.lng,obse.lat))

                .withIconImage(getDrawable(R.drawable.red_marker)!!.toBitmap())

        }

    }


}*/






