package com.orozbek.rickandmortyapi.ui.fragments.locationFragmnet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.orozbek.rickandmortyapi.R
import com.orozbek.rickandmortyapi.data.network.Status
import com.orozbek.rickandmortyapi.databinding.FragmentCharacterBinding
import com.orozbek.rickandmortyapi.databinding.FragmentLocationBinding


class LocationFragment : Fragment() {

    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private val locAdapter = LocationAdapter()
    private lateinit var viewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
    }

    private fun initialize() {
        viewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        viewModel.fetchLocation(1).observe(viewLifecycleOwner, Observer {resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    resource.data?.results?.let { locAdapter.addItems(it) }
                }
            }
        })
    }

    private fun setupViews() {
        binding.locationRv.adapter = locAdapter
    }

}