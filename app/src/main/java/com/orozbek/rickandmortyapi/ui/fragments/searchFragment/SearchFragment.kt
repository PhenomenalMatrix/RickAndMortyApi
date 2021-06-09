package com.orozbek.rickandmortyapi.ui.fragments.searchFragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.orozbek.rickandmortyapi.R
import com.orozbek.rickandmortyapi.data.network.Status
import com.orozbek.rickandmortyapi.databinding.FragmentSearchBinding
import com.orozbek.rickandmortyapi.models.Character
import com.orozbek.rickandmortyapi.models.GeneralModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList


class SearchFragment : Fragment(), SearchAdapter.OnItemClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModel()
    private val adapter = SearchAdapter()
    private lateinit var name: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
    }

    private fun initialize() {
        binding.searchRv.adapter = adapter
        adapter.addListener(this)
    }

    private fun setupViews() {
        binding.searchBtn.setOnClickListener {
            adapter.clearAdapter()
            dismissKeyboard(requireActivity())
            name = binding.searchEt.text.toString()
            fetchData(name)
        }
    }

    private fun fetchData(name: String) {
        viewModel.fetchFilteredData(name).observe(viewLifecycleOwner, Observer {resources ->
            when(resources.status){
                Status.SUCCESS ->{
                    resources.data?.results?.let { adapter.addItems(it as ArrayList<Character>) }
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),"character error",Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.fetchFilteredDataLoc(name).observe(viewLifecycleOwner, Observer {resources ->
            when(resources.status){
                Status.SUCCESS ->{
                    resources.data?.results?.let { adapter.addItems(it as ArrayList<Character>) }
                }

            }
        })
        viewModel.fetchFilteredDataEpi(name).observe(viewLifecycleOwner, Observer {resources ->
            when(resources.status){
                Status.SUCCESS ->{
                    resources.data?.results?.let { adapter.addItems(it as ArrayList<Character>)}
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),"epi error",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun dismissKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != activity.currentFocus) imm.hideSoftInputFromWindow(
            activity.currentFocus!!.applicationWindowToken, 0
        )
    }

    override fun onClick(id: Int, type: Int) {
        val bundle = Bundle()
        bundle.putInt("idKey",id)
        bundle.putInt("typeKey",type)
        findNavController().navigate(R.id.detailsFragment,bundle)
    }


}