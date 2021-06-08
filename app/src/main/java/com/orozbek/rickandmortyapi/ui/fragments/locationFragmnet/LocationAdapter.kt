package com.orozbek.rickandmortyapi.ui.fragments.locationFragmnet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orozbek.rickandmortyapi.databinding.LocationItemBinding
import com.orozbek.rickandmortyapi.models.LocationModel
import java.util.*

class LocationAdapter: RecyclerView.Adapter<LocationAdapter.ViewHolder>(){

    private var list: List<LocationModel> = LinkedList()

    open fun addItems(list: List<LocationModel>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LocationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class ViewHolder(private val binding: LocationItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(locationModel: LocationModel) {
            binding.locationNameTv.setText(locationModel.name)
            binding.locationCreatedTv.setText(locationModel.created)
            binding.locationTypeTv.setText(locationModel.type)
        }

    }

}