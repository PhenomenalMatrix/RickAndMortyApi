package com.orozbek.rickandmortyapi.ui.fragments.searchFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orozbek.rickandmortyapi.constants.const
import com.orozbek.rickandmortyapi.databinding.ChartersItemBinding
import com.orozbek.rickandmortyapi.databinding.LocationItemBinding
import com.orozbek.rickandmortyapi.models.Character
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val TYPE_WITH_IMAGE = 0
const val TYPE_WITHOUT_IMAGE = 1

class SearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: ArrayList<Character> = ArrayList()
    private lateinit var listener: OnItemClickListener

    fun addListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun clearAdapter() {
        if (!this.list.isEmpty()) {
            this.list.clear()
        }
    }

    fun addItems(list: ArrayList<Character>) {
        this.list.addAll(list)
        this.list.sortWith(compareBy {
            val format = SimpleDateFormat()
            format.applyPattern("yyyy-dd-mm")
            val docDate: Date = format.parse(it.created)
            docDate
        })
        this.list.reverse()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_WITH_IMAGE) {
            val binding =
                ChartersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Viewholder(binding)
        } else {
            val binding =
                LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LockAndEpisodesViewHolder(binding)
        }
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_WITH_IMAGE) {
            (holder as Viewholder).onBind(list[position], listener)
        } else {
            (holder as LockAndEpisodesViewHolder).onBind(list[position], listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].image.isNullOrEmpty()) {
            TYPE_WITHOUT_IMAGE
        } else {
            TYPE_WITH_IMAGE
        }
    }

    class LockAndEpisodesViewHolder(val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            general: Character,
            listener: OnItemClickListener
        ) {
            if (!general.air_date.isNullOrEmpty()) {
                binding.titleTypeTv.setText("Air date:")
                binding.locationNameTv.setText(general.name)
                binding.locationTypeTv.setText(general.air_date)
                binding.locationCreatedTv.setText(general.created)
            } else {
                binding.titleTypeTv.setText("Type:")
                binding.locationNameTv.setText(general.name)
                binding.locationTypeTv.setText(general.type)
                binding.locationCreatedTv.setText(general.created)
            }
            binding.root.setOnClickListener {
                if (!general.air_date.isNullOrEmpty()) {
                    general.id?.let { it1 ->
                        listener.onClick(it1, const.EPISODE)
                    }
                } else {
                    general.id?.let { it1 ->
                        listener.onClick(it1, const.LOCATION)
                    }
                }
            }
        }

    }

    class Viewholder(val binding: ChartersItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun onBind(
            character: Character,
            listener: OnItemClickListener
        ) {
            binding.charterNameTv.setText(character.name)
            binding.charterCreatedTv.setText(character.created)
            Glide.with(binding.chartersIv.context).load(character.image).centerCrop()
                .into(binding.chartersIv)
            binding.root.setOnClickListener {
                character.id?.let { it1 -> listener.onClick(it1, const.CHARACTER) }
            }
        }

    }

    interface OnItemClickListener {
        fun onClick(id: Int, type: Int)
    }

}