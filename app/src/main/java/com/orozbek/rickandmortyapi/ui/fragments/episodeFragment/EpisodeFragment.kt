package com.orozbek.rickandmortyapi.ui.fragments.episodeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.orozbek.rickandmortyapi.data.network.Status
import com.orozbek.rickandmortyapi.databinding.FragmentEpisodeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel



class EpisodeFragment : Fragment() {

    private val episodeAdapter = EpisodeAdapter()
    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EpisodeViewModel by viewModel()

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
        viewModel.fetchEpisodes(1).observe(viewLifecycleOwner, Observer {resourse ->
            when(resourse.status){
                Status.SUCCESS ->{
                    resourse.data?.results?.let { episodeAdapter.addItems(it) }
                    binding.episodeProgressBar.visibility = View.GONE
                }
                Status.LOADING ->{
                    binding.episodeProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}