package com.orozbek.rickandmortyapi.ui.fragments.episodeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orozbek.rickandmortyapi.constants.const
import com.orozbek.rickandmortyapi.databinding.LocationItemBinding
import com.orozbek.rickandmortyapi.models.Episodes
import java.util.*

class EpisodeAdapter: RecyclerView.Adapter<EpisodeAdapter.ViewHolder>(){

    private var list: List<Episodes> = LinkedList()
    private lateinit var listener: EpisodeAdapter.OnItemClickListener

    fun addListener(listener: EpisodeAdapter.OnItemClickListener) {
        this.listener = listener
    }

    open fun addItems(list: List<Episodes>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeAdapter.ViewHolder {
        val binding = LocationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: EpisodeAdapter.ViewHolder, position: Int) {
        holder.onBind(list[position],listener)
    }

    class ViewHolder(private val binding: LocationItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(episodes: Episodes, listener: OnItemClickListener) {
            binding.titleTypeTv.setText("Air date:")
            binding.locationNameTv.setText(episodes.name)
            binding.locationTypeTv.setText(episodes.air_date)
            binding.locationCreatedTv.setText(episodes.created)
            binding.root.setOnClickListener {
                listener.onClick(episodes.id,const.EPISODE)
            }
        }

    }

    interface OnItemClickListener {
        fun onClick(id: Int, type: Int)
    }

}