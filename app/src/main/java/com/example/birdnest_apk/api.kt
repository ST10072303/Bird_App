package com.example.birdnest_apk

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL2 = "https://api.ebird.org"
object api {
    val ebirdRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL2)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(eBeird::class.java)
}