package com.orozbek.rickandmortyapi.ui.fragments.episodeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.orozbek.rickandmortyapi.R
import com.orozbek.rickandmortyapi.data.network.Status
import com.orozbek.rickandmortyapi.databinding.FragmentEpisodeBinding



class EpisodeFragment : Fragment() {

    private val episodeAdapter = EpisodeAdapter()
    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EpisodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()

    }

    private fun setupViews() {
        binding.episodesRv.adapter = episodeAdapter
    }

    private fun initialize() {
        viewModel = ViewModelProvider(this).get(EpisodeViewModel::class.java)
        viewModel.fetchEpisodes(1).observe(viewLifecycleOwner, Observer {resourse ->
            when(resourse.status){
                Status.SUCCESS ->{
                    resourse.data?.results?.let { episodeAdapter.addItems(it) }
                }
            }
        })
    }

}