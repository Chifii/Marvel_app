package com.intermedia.marvel.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.marvel.databinding.EventFragmentBinding
import com.intermedia.marvel.home.domain.models.ResultsModel
import com.intermedia.marvel.home.presentation.view.adapters.EventsAdapter
import com.intermedia.marvel.home.presentation.viewmodel.EventViewModel

class EventFragment : Fragment() {
    private lateinit var binding: EventFragmentBinding
    private var events: MutableList<ResultsModel> = mutableListOf()
    val model: EventViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EventFragmentBinding.bind(view)

        model.events.observe(viewLifecycleOwner) { setupView(it) }
        model.moreEvents.observe(viewLifecycleOwner) { moreEvents(it) }

        model.getEventsData()
    }

    private fun setupView(data: List<ResultsModel>) {
        populateList(data)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        val rvAdapter = EventsAdapter(events, itemClicked)

        binding.apply {
            EventsRecyclerView.layoutManager = layoutManager
            EventsRecyclerView.adapter = rvAdapter
            EventsRecyclerView.setHasFixedSize(true)

            EventsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        model.getEventsData(LIMIT)
                    }
                }
            })
        }
    }

    private fun moreEvents(data: List<ResultsModel>) {
        populateList(data)
        binding.EventsRecyclerView.adapter?.notifyItemRangeInserted(
            events.size - LIMIT,
            events.size
        )
    }

    private fun populateList(data: List<ResultsModel>) {
        data.forEach { events.add(it) }
    }

    private object itemClicked : EventsAdapter.onItemClickListener {
        override fun onItemClick(position: Int) {
            // Not necessary
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = EventFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private companion object {
        const val LIMIT = 15
    }
}