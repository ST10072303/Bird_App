package com.example.birdnest_apk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.birdnest_apk.models.ebird.ObservationsItem
import kotlinx.coroutines.launch
import retrofit2.Response

class birds:ViewModel() {
    val data =  MutableLiveData<List<ObservationsItem>>()
    val error = MutableLiveData<String>()

    fun fetchBirdData(lat: String, lng: String, key: String, hotspot: String){


        viewModelScope.launch{
            val response: Response<List<ObservationsItem>> =api.ebirdRetrofit.getBirdInfo(lat,lng,key,hotspot)
            if (response.isSuccessful){
                data.postValue(response.body())
            }else{
                error.postValue("Failed to load data: ${response.message()}")
            }
        }
    }
}