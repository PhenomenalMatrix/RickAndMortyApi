package com.orozbek.rickandmortyapi.ui.fragments.charterFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orozbek.rickandmortyapi.R
import com.orozbek.rickandmortyapi.constants.const
import com.orozbek.rickandmortyapi.databinding.ChartersItemBinding
import com.orozbek.rickandmortyapi.models.Character
import java.util.*

class CharactersAdapter: RecyclerView.Adapter<CharactersAdapter.ViewHolder>(){

    private var list: List<Character> = LinkedList()

    private lateinit var listener: CharactersAdapter.OnItemClickListener

    fun addListener(listener: CharactersAdapter.OnItemClickListener) {
        this.listener = listener
    }

    open fun addItems(list: List<Character>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapter.ViewHolder {
        val binding = ChartersItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: CharactersAdapter.ViewHolder, position: Int) {
        holder.onBind(list[position],listener)
    }

    class ViewHolder(val binding: ChartersItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(character: Character, listener: OnItemClickListener) {
            Glide.with(binding.chartersIv.context).load(character.image)
                    .centerCrop().placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.chartersIv);
            binding.charterNameTv.setText(character.name)
            binding.charterStatusTv.setText(character.status)
            binding.charterCreatedTv.setText(character.created)
            binding.root.setOnClickListener {
                character.id?.let { it1 -> listener.onClick(it1,const.CHARACTER) }
            }
        }

    }

    interface OnItemClickListener {
        fun onClick(id: Int, type: Int)
    }

}