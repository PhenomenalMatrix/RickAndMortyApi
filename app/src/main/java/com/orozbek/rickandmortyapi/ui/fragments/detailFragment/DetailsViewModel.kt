package com.orozbek.rickandmortyapi.ui.fragments.detailFragment

import androidx.lifecycle.LiveData
import com.orozbek.rickandmortyapi.base.BaseViewModel
import com.orozbek.rickandmortyapi.data.network.Resource
import com.orozbek.rickandmortyapi.data.repository.Repository
import com.orozbek.rickandmortyapi.models.*

class DetailsViewModel(private val repository: Repository): BaseViewModel(){

    fun fetchCharactersId(id: Int): LiveData<Resource<Character>> {
        return repository.fetchRickAndMortyApiCharactersId(id)
    }
    fun fetchLocationId(id: Int): LiveData<Resource<LocationModel>> {
        return repository.fetchRickAndMortyApiLocationsId(id)
    }
    fun fetchEpisodeId(id: Int): LiveData<Resource<Episodes>> {
        return repository.fetchRickAndMortyApiEpisodesId(id)
    }

}