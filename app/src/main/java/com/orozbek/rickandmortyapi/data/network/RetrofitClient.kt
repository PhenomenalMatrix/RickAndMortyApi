package com.orozbek.rickandmortyapi.data.network

import com.orozbek.rickandmortyapi.constants.const
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {

        fun create(): RickAndMortyApiService{
             val okHttpClient: OkHttpClient = OkHttpClient()
                .newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

             val retrofit = Retrofit.Builder()
                .baseUrl(const.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RickAndMortyApiService::class.java)

        }
    }
}