package com.orozbek.rickandmortyapi.ui.fragments.episodeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.orozbek.rickandmortyapi.App
import com.orozbek.rickandmortyapi.data.network.Resource
import com.orozbek.rickandmortyapi.models.Episodes
import com.orozbek.rickandmortyapi.models.MainResponse

class EpisodeViewModel: ViewModel(){
    fun fetchEpisodes(page: Int): LiveData<Resource<MainResponse<Episodes>>>{
        return App.repository.fetchRickAndMortyApiEpisodes(page)
    }
}