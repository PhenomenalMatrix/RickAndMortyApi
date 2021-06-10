package com.orozbek.rickandmortyapi.ui.fragments.detailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.orozbek.rickandmortyapi.constants.const
import com.orozbek.rickandmortyapi.data.network.Status
import com.orozbek.rickandmortyapi.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModel()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var idItem: Int = 0
    private var type: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
    }

    private fun initialize() {
        idItem = arguments?.getInt("idKey")!!
        type = arguments?.getInt("typeKey")!!
        frameCheker(type)
    }

    private fun frameCheker(type: Int) {
        when (type) {
            const.CHARACTER -> {
                binding.contForLocEp.visibility = View.GONE
                binding.contForCharters.visibility = View.VISIBLE
                observeCharacter()
            }
            const.LOCATION -> {
                binding.contForCharters.visibility = View.GONE
                binding.contForLocEp.visibility = View.VISIBLE
                binding.containerForEpisodesTv.visibility = View.GONE
                binding.contForLocTv.visibility = View.VISIBLE
                observeLocation()
            }
            const.EPISODE -> {
                binding.contForCharters.visibility = View.GONE
                binding.contForLocEp.visibility = View.VISIBLE
                binding.containerForLocationTv.visibility = View.GONE
                binding.containerForEpisodesTv.visibility = View.VISIBLE
                observeEpisode()
            }
        }
    }

    private fun observeEpisode() {
        viewModel.fetchEpisodeId(idItem).observe(viewLifecycleOwner, Observer { resources ->
            when (resources.status) {
                Status.SUCCESS -> {
                    binding.nameTv.setText(resources.data?.name)
                    binding.airDateTv.setText(resources.data?.air_date)
                    binding.episodeTv.setText(resources.data?.episode)
                    binding.createdEpisodeTv.setText(resources.data?.created)
                }
            }
        })
    }

    private fun observeLocation() {
        viewModel.fetchLocationId(idItem).observe(viewLifecycleOwner, Observer { resources ->
            when (resources.status) {
                Status.SUCCESS -> {
                    binding.nameTv.setText(resources.data?.name)
                    binding.typeTv.setText(resources.data?.type)
                    binding.dimensionTv.setText(resources.data?.dimension)
                    binding.createdTv.setText(resources.data?.created)
                }
            }
        })
    }


    private fun observeCharacter() {
        viewModel.fetchCharactersId(idItem)
            .observe(viewLifecycleOwner, Observer { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        Glide.with(requireContext()).load(resources.data?.image)
                            .centerCrop().into(binding.detailsIv)
                        binding.nameCharacterTv.setText(resources.data?.name)
                        binding.speciesTv.setText(resources.data?.species)
                        binding.genderTv.setText(resources.data?.gender)
                        binding.createdCharacterTv.setText(resources.data?.created)
                    }
                }
            })
    }

    private fun setupViews() {
    }


}