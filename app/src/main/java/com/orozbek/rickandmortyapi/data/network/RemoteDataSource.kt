package com.orozbek.rickandmortyapi.data.network

import com.orozbek.rickandmortyapi.base.BaseDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val apiService: RickAndMortyApiService): BaseDataSource(){

    suspend fun fetchCharacters(page: Int) = getResult {
        apiService.fetchCharacters(page)
    }

    suspend fun fetchLocations(page: Int) = getResult {
        apiService.fetchLocations(page)
    }

    suspend fun fetchEpisodes(page: Int) = getResult {
        apiService.fetchEpisodes(page)
    }

    suspend fun fetchFilteredData(name: String) = getResult {
        apiService.fetchFilteredData(name)
    }
    suspend fun fetchFilteredDataloc(name: String) = getResult {
        apiService.fetchFilteredDataLoc(name)
    }
    suspend fun fetchFilteredDataEpi(name: String) = getResult {
        apiService.fetchFilteredDataEpisode(name)
    }

    suspend fun fetchCharactersId(id: Int) = getResult {
        apiService.fetchCharactersId(id)
    }

    suspend fun fetchLocationsId(id: Int) = getResult {
        apiService.fetchLocationsId(id)
    }

    suspend fun fetchEpisodesId(id: Int) = getResult {
        apiService.fetchEpisodesId(id)
    }

}