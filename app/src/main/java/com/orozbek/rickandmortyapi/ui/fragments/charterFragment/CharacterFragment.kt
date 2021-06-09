package com.orozbek.rickandmortyapi.ui.fragments.charterFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.orozbek.rickandmortyapi.data.network.Status
import com.orozbek.rickandmortyapi.databinding.FragmentCharacterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterFragment: Fragment() {

    private val chareterAdapter = CharactersAdapter()
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModel()


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
        initialize()
        setupViews()
    }

    fun initialize() {
        viewModel.fetchCharacters(1).observe(viewLifecycleOwner, androidx.lifecycle.Observer {resources ->
            when(resources.status){
                Status.SUCCESS ->{
                    resources.data?.let { chareterAdapter.addItems(it.results) }
                    binding.charterProgressBar.visibility = View.GONE
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

    fun setupViews() {
        binding.chartersRv.adapter = chareterAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}
