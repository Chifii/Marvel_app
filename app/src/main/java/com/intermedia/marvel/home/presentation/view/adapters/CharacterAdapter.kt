package com.intermedia.marvel.home.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intermedia.marvel.databinding.CharactersCardsBinding
import com.intermedia.marvel.databinding.EventsCardsBinding
import com.intermedia.marvel.home.domain.models.ResultsModel

class CharacterAdapter(
    private val character: List<ResultsModel>
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CharactersCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, mListener)
    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(character[position])
    }

    override fun getItemCount(): Int = character.size

    class ViewHolder(
        val binding: CharactersCardsBinding,
        private val listener: onItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.homeCardContainer.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bind(character: ResultsModel) {
            binding.apply {
                characterName.text = character.name
                characterDescriptionText.text = character.description

                val imgUrl = character.thumbnail?.path + "." + character.thumbnail?.extension

                Glide.with(this.root)
                    .load(imgUrl)
                    .centerCrop()
                    .into(characterCardImg)
            }
        }
    }
}