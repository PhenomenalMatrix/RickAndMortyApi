package com.orozbek.rickandmortyapi.ui.fragments.locationFragmnet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.orozbek.rickandmortyapi.data.network.Status
import com.orozbek.rickandmortyapi.databinding.FragmentLocationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocationFragment : Fragment() {

    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private val locAdapter = LocationAdapter()
    private val viewModel: LocationViewModel by viewModel()

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
        viewModel.fetchLocation(1).observe(viewLifecycleOwner, Observer {resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    resource.data?.results?.let { locAdapter.addItems(it) }
                    binding.locationProgressBar.visibility = View.GONE
                }
                Status.LOADING ->{
                    binding.locationProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupViews() {
        binding.locationRv.adapter = locAdapter
    }

}