package com.orozbek.rickandmortyapi.data.network

import com.orozbek.rickandmortyapi.base.BaseDataSource

class RemoteDataSource: BaseDataSource(){
    private var apiService: RickAndMortyApiService = RetrofitClient.create()

    suspend fun fetchCharacters(page: Int) = getResult {
        apiService.fetchCharacters(page)
    }

    suspend fun fetchLocations(page: Int) = getResult {
        apiService.fetchLocations(page)
    }

    suspend fun fetchEpisodes(page: Int) = getResult {
        apiService.fetchEpisodes(page)
    }
}