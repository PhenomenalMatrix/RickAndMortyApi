package com.orozbek.rickandmortyapi.di

import com.orozbek.rickandmortyapi.ui.fragments.charterFragment.CharactersViewModel
import com.orozbek.rickandmortyapi.ui.fragments.detailFragment.DetailsViewModel
import com.orozbek.rickandmortyapi.ui.fragments.episodeFragment.EpisodeViewModel
import com.orozbek.rickandmortyapi.ui.fragments.locationFragmnet.LocationViewModel
import com.orozbek.rickandmortyapi.ui.fragments.searchFragment.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}