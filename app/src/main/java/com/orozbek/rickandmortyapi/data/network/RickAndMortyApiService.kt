package com.orozbek.rickandmortyapi.data.network

import com.orozbek.rickandmortyapi.models.MainResponse
import com.orozbek.rickandmortyapi.models.Character
import com.orozbek.rickandmortyapi.models.Episodes
import com.orozbek.rickandmortyapi.models.LocationModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("/api/character")
    suspend fun fetchCharacters(@Query("page") page: Int): Response<MainResponse<Character>>

    @GET("/api/location")
    suspend fun fetchLocations(@Query("page") page: Int): Response<MainResponse<LocationModel>>

    @GET("/api/episode")
    suspend fun fetchEpisodes(@Query("page") page: Int): Response<MainResponse<Episodes>>

}