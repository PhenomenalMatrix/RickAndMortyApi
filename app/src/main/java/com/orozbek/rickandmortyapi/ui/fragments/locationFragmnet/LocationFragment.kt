package com.orozbek.rickandmortyapi.ui.fragments.locationFragmnet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orozbek.rickandmortyapi.R
import com.orozbek.rickandmortyapi.data.network.Status
import com.orozbek.rickandmortyapi.databinding.FragmentLocationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocationFragment : Fragment(),LocationAdapter.OnItemClickListener {

    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private val locAdapter = LocationAdapter()
    private val viewModel: LocationViewModel by viewModel()
    private  var nextPageToken: Int = 0

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
        locAdapter.addListener(this)
        initialize()
        setRecyclerViewScrollListener()
        setupViews()
    }

    private fun initialize() {
        viewModel.fetchLocation(nextPageToken).observe(viewLifecycleOwner, Observer {resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    resource.data?.results?.let { locAdapter.addItems(it) }
                    binding.locationProgressBar.visibility = View.GONE
                    val string = resource.data?.info?.next
                    val result = string?.replace(
                        "https://rickandmortyapi.com/api/location?page=",
                        "",
                        true
                    )
                    if (result !=null) {
                        nextPageToken = result?.toInt()
                    }else{
                        nextPageToken = 0
                    }
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

    private fun setRecyclerViewScrollListener() {
        binding.locationRv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = binding.locationRv.getLayoutManager() as LinearLayoutManager
                val pos = layoutManager.findLastCompletelyVisibleItemPosition()
                val numItems: Int = binding.locationRv.getAdapter()!!.getItemCount()
                if (pos+1 == numItems) {
                    initialize()
                    Log.e("TAG", "onScrollStateChanged: " + pos)
                }
            }
        })
    }

    private fun setupViews() {
        binding.locationRv.adapter = locAdapter
    }

    override fun onClick(id: Int, type: Int) {
        val bundle = Bundle()
        bundle.putInt("idKey",id)
        bundle.putInt("typeKey",type)
        findNavController().navigate(R.id.detailsFragment,bundle)
    }

}