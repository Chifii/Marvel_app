package com.intermedia.marvel.home.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intermedia.marvel.R
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
        return ViewHolder(binding, mListener, parent)
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
        private val listener: onItemClickListener,
        val parent: ViewGroup
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.homeCardContainer.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bind(event: ResultsModel) {
            binding.apply {
                eventName.text = event.title
                evenReleaseText.text = event.start

                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(parent.context, RecyclerView.VERTICAL, false)
                val rvAdapter = event.comics?.items?.let { DetailAdapter(it) }
                eventComicRecycler.layoutManager = layoutManager
                eventComicRecycler.adapter = rvAdapter

                val imgUrl = event.thumbnail?.path + "." + event.thumbnail?.extension

                Glide.with(this.root)
                    .load(imgUrl)
                    .centerCrop()
                    .into(eventCardImg)

                arrowButton.setOnClickListener {
                    if (hiddenView.visibility == View.VISIBLE) {
                        arrowButton.setImageResource(R.drawable.expand_more)
                        hiddenView.visibility = View.GONE
                    } else {
                        arrowButton.setImageResource(R.drawable.expand_less)
                        hiddenView.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}