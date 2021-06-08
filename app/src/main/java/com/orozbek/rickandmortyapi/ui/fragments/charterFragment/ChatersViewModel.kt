package com.orozbek.rickandmortyapi.ui.fragments.charterFragment

import androidx.lifecycle.LiveData
import com.orozbek.rickandmortyapi.App
import com.orozbek.rickandmortyapi.base.BaseViewModel
import com.orozbek.rickandmortyapi.data.network.Resource
import com.orozbek.rickandmortyapi.models.MainResponse
import com.orozbek.rickandmortyapi.models.Character

class ChatersViewModel : BaseViewModel(){

    fun fetchCharacters(page: Int): LiveData<Resource<MainResponse<Character>>>{
        return App.repository.fetchRickAndMortyApiCharacters(page)
    }

}