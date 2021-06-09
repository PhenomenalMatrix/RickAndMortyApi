package com.orozbek.rickandmortyapi.ui.fragments.searchFragment

import androidx.lifecycle.LiveData
import com.orozbek.rickandmortyapi.base.BaseViewModel
import com.orozbek.rickandmortyapi.data.network.Resource
import com.orozbek.rickandmortyapi.data.repository.Repository
import com.orozbek.rickandmortyapi.models.Character
import com.orozbek.rickandmortyapi.models.GeneralModel
import com.orozbek.rickandmortyapi.models.MainResponse


class SearchViewModel(private val repository: Repository): BaseViewModel() {

    fun fetchFilteredData(name: String): LiveData<Resource<MainResponse<Character>>> {
        return repository.fetchRickAndMortyApiFilteredData(name)
    }

    fun fetchFilteredDataLoc(name: String): LiveData<Resource<MainResponse<Character>>> {
        return repository.fetchRickAndMortyApiFilteredDataLoc(name)
    }

    fun fetchFilteredDataEpi(name: String): LiveData<Resource<MainResponse<Character>>> {
        return repository.fetchRickAndMortyApiFilteredDataEpi(name)
    }
}