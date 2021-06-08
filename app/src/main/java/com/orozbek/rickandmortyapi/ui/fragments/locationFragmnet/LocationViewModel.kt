package com.orozbek.rickandmortyapi.ui.fragments.locationFragmnet

import androidx.lifecycle.LiveData
import com.orozbek.rickandmortyapi.App
import com.orozbek.rickandmortyapi.base.BaseViewModel
import com.orozbek.rickandmortyapi.data.network.Resource
import com.orozbek.rickandmortyapi.models.LocationModel
import com.orozbek.rickandmortyapi.models.MainResponse


class LocationViewModel: BaseViewModel(){
    fun fetchLocation(page: Int): LiveData<Resource<MainResponse<LocationModel>>>{
        return App.repository.fetchRickAndMortyApiLocations(page)
    }
}