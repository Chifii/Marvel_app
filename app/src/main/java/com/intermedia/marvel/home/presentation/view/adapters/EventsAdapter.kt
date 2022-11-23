package com.intermedia.marvel.home.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intermedia.marvel.databinding.EventsCardsBinding
import com.intermedia.marvel.home.domain.models.ResultsModel

class EventsAdapter(
    private val events: List<ResultsModel>,
    itemClickListener: onItemClickListener
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    private var mListener: onItemClickListener = itemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EventsCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, mListener)
    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size

    class ViewHolder(
        val binding: EventsCardsBinding,
        private val listener: onItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.homeCardContainer.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bind(event: ResultsModel) {
            binding.apply {
                eventName.text = event.name
                evenReleaseText.text = event.start

                val imgUrl = event.thumbnail?.path + "." + event.thumbnail?.extension

                Glide.with(this.root)
                    .load(imgUrl)
                    .centerCrop()
                    .into(eventCardImg)

                arrowButton.setOnClickListener {
                    hiddenView.visibility = View.VISIBLE
                }
            }
        }
    }
}