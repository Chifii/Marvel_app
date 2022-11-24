package com.intermedia.marvel.home.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intermedia.marvel.databinding.DetailCardsBinding
import com.intermedia.marvel.home.domain.models.Item
import com.intermedia.marvel.home.domain.models.ResultsModel

class DetailAdapter(
    private val comic: List<Item>
) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DetailCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(comic[position])
    }

    override fun getItemCount(): Int = comic.size

    class ViewHolder(
        private val binding: DetailCardsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(detail: Item) {
            binding.apply {
                detailCardTitle.text = detail.name
                // detailCardDate.text = detail.start
            }
        }
    }
}