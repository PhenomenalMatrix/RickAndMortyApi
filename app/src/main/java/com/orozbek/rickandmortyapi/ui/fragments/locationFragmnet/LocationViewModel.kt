package com.orozbek.rickandmortyapi.ui.fragments.locationFragmnet

import androidx.lifecycle.LiveData
import com.orozbek.rickandmortyapi.App
import com.orozbek.rickandmortyapi.base.BaseViewModel
import com.orozbek.rickandmortyapi.data.network.Resource
import com.orozbek.rickandmortyapi.data.repository.Repository
import com.orozbek.rickandmortyapi.models.LocationModel
import com.orozbek.rickandmortyapi.models.MainResponse


class LocationViewModel(private val repository: Repository): BaseViewModel(){
    fun fetchLocation(page: Int): LiveData<Resource<MainResponse<LocationModel>>>{
        return repository.fetchRickAndMortyApiLocations(page)
    }
}