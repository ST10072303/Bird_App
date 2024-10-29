package com.example.birdnest_apk

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import com.example.birdnest_apk.models.ebird.Observations
import com.example.birdnest_apk.models.ebird.ObservationsItem

interface eBeird {
    @GET("/v2/data/obs/geo/recent")
    suspend fun getBirdInfo(@Query("lat") lat:String, @Query("lng") lng:String, @Query("key") key:String, @Query("hotspot") hotspot:String ):Response<List<ObservationsItem>>

}