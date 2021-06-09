package com.orozbek.rickandmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.orozbek.rickandmortyapi.data.network.RemoteDataSource
import com.orozbek.rickandmortyapi.data.network.Resource
import com.orozbek.rickandmortyapi.models.*
import kotlinx.coroutines.Dispatchers
import com.orozbek.rickandmortyapi.models.GeneralModel

class Repository (private val dataSource: RemoteDataSource){

    fun fetchRickAndMortyApiCharacters(page: Int): LiveData<Resource<MainResponse<Character>>> = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        val response = dataSource.fetchCharacters(page)
        emit(response)
    }

    fun fetchRickAndMortyApiLocations(page:Int): LiveData<Resource<MainResponse<LocationModel>>> = liveData (Dispatchers.IO ){
        emit(Resource.loading(null))
        val response = dataSource.fetchLocations(page)
        emit(response)
    }

    fun fetchRickAndMortyApiEpisodes(page:Int): LiveData<Resource<MainResponse<Episodes>>> = liveData (Dispatchers.IO ){
        emit(Resource.loading(null))
        val response = dataSource.fetchEpisodes(page)
        emit(response)
    }

    fun fetchRickAndMortyApiFilteredData(name: String): LiveData<Resource<MainResponse<Character>>> = liveData (Dispatchers.IO ) {
        emit(Resource.loading(null))
        val response = dataSource.fetchFilteredData(name)
        emit(response)
    }
    fun fetchRickAndMortyApiFilteredDataLoc(name: String): LiveData<Resource<MainResponse<Character>>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val response = dataSource.fetchFilteredDataloc(name)
        emit(response)
    }
    fun fetchRickAndMortyApiFilteredDataEpi(name: String): LiveData<Resource<MainResponse<Character>>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val response = dataSource.fetchFilteredDataEpi(name)
        emit(response)
    }

}