package com.orozbek.rickandmortyapi

import android.app.Application
import com.orozbek.rickandmortyapi.data.network.RemoteDataSource
import com.orozbek.rickandmortyapi.data.repository.Repository

class App: Application(){
    companion object{
        val repository = Repository(RemoteDataSource())
    }
}