package com.orozbek.rickandmortyapi.di

import com.orozbek.rickandmortyapi.data.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { Repository(get()) }
}