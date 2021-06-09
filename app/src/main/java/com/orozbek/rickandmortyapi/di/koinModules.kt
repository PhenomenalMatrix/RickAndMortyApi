package com.orozbek.rickandmortyapi.di

import com.orozbek.rickandmortyapi.data.network.networkModule
import com.orozbek.rickandmortyapi.data.network.remoteDataSourceModule

val koinModules = listOf(
    networkModule,
    remoteDataSourceModule,
    repoModules,
    viewModules
)