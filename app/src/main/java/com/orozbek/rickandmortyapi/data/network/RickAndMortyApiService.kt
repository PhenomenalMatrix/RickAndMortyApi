package com.orozbek.rickandmortyapi.data.network

import com.orozbek.rickandmortyapi.models.*
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

    @GET("/api/character")
    suspend fun fetchFilteredData(@Query("name") name: String): Response<MainResponse<Character>>
    
    @GET("/api/location")
    suspend fun fetchFilteredDataLoc(@Query("name") name: String): Response<MainResponse<Character>>
    
    @GET("/api/episode")
    suspend fun fetchFilteredDataEpisode(@Query("name") name: String): Response<MainResponse<Character>>

}