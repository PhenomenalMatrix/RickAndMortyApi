package com.orozbek.rickandmortyapi.ui.fragments.detailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.orozbek.rickandmortyapi.R
import com.orozbek.rickandmortyapi.constants.const
import com.orozbek.rickandmortyapi.data.network.Status
import com.orozbek.rickandmortyapi.databinding.FragmentDetailsBinding
import com.orozbek.rickandmortyapi.models.Character
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModel()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var idItem: Int = 0
    private var type: Int = 0
    private var list: ArrayList<Any> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
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
        when(type){
            const.CHARACTER ->{
                binding.contForLocEp.visibility = View.GONE
                binding.contForCharters.visibility = View.VISIBLE
                observeCharacter()
            }
            const.LOCATION ->{
                binding.contForCharters.visibility = View.GONE
                binding.contForLocEp.visibility = View.VISIBLE
                binding.containerForEpisodesTv.visibility = View.GONE
                binding.contForLocTv.visibility = View.VISIBLE
            }
            const.EPISODE ->{
                binding.contForCharters.visibility = View.GONE
                binding.contForLocEp.visibility = View.VISIBLE
                binding.containerForLocationTv.visibility = View.GONE
                binding.containerForEpisodesTv.visibility = View.VISIBLE
            }
        }
    }

    private fun observeCharacter() {
        viewModel.fetchCharactersId(idItem).observe(viewLifecycleOwner, Observer {resources ->
            when(resources.status){
                Status.SUCCESS ->{
                    Glide.with(requireContext()).load(resources.data?.image).centerCrop().into(binding.detailsIv)
                    binding.nameCharacterTv.setText(resources.data?.name)
                    binding.speciesTv.setText(resources.data?.species)
                    binding.genderTv.setText(resources.data?.gender)
                    binding.createdCharacterTv.setText(resources.data?.created)
                }
            }
        })
    }

    private fun setupViews() {
        Toast.makeText(requireContext(),idItem.toString()+" "+type.toString(),Toast.LENGTH_SHORT).show()
    }


}