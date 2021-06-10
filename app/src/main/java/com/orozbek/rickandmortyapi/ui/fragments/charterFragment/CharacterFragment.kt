package com.orozbek.rickandmortyapi.ui.fragments.charterFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orozbek.rickandmortyapi.R
import com.orozbek.rickandmortyapi.data.network.Status
import com.orozbek.rickandmortyapi.databinding.FragmentCharacterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterFragment: Fragment(), CharactersAdapter.OnItemClickListener {

    private val chareterAdapter = CharactersAdapter()
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModel()
    private  var nextPageToken: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chareterAdapter.addListener(this)
        initialize()
        setRecyclerViewScrollListener()
        setupViews()
    }

    fun initialize() {
        viewModel.fetchCharacters(nextPageToken).observe(viewLifecycleOwner, androidx.lifecycle.Observer {resources ->
            when(resources.status){
                Status.SUCCESS ->{
                    resources.data?.let { chareterAdapter.addItems(it.results) }
                    binding.charterProgressBar.visibility = View.GONE
                    val string = resources.data?.info?.next
                    val result = string?.replace(
                        "https://rickandmortyapi.com/api/character?page=",
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
                    binding.charterProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun setRecyclerViewScrollListener() {
        binding.chartersRv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = binding.chartersRv.getLayoutManager() as LinearLayoutManager
                val pos = layoutManager.findLastCompletelyVisibleItemPosition()
                val numItems: Int = binding.chartersRv.getAdapter()!!.getItemCount()
                if (pos+1 == numItems) {
                    initialize()
                    Log.e("TAG", "onScrollStateChanged: " + pos)
                }
            }
        })
    }

    fun setupViews() {
        binding.chartersRv.adapter = chareterAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onClick(id: Int, type: Int) {
        val bundle = Bundle()
        bundle.putInt("idKey",id)
        bundle.putInt("typeKey",type)
        findNavController().navigate(R.id.detailsFragment,bundle)
    }
}
