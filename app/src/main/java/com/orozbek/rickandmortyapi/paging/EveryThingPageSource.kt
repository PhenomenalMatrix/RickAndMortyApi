package com.orozbek.rickandmortyapi.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orozbek.rickandmortyapi.data.network.RickAndMortyApiService
import com.orozbek.rickandmortyapi.models.Character


//class EveryThingPageSource(private val service: RickAndMortyApiService, private val query: String): PagingSource<Int, Character>() {
//
//    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
//            if (query.isEmpty()){
//                return LoadResult.Page(emptyList(),prevKey = null,nextKey = null)
//            }
//
//        val page: Int = params.key ?: 1
//        val pageSize: Int = params.loadSize
//
//        val response = service.fetchCharacters(page)
//    }
//}