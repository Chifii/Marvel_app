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
    val model: EventViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EventFragmentBinding.bind(view)

        model.events.observe(viewLifecycleOwner) { setupView(it) }

        model.getEventsData()
    }

    private fun setupView(data: List<ResultsModel>) {
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        val rvAdapter = EventsAdapter(data, itemClicked)

        binding.apply {
            EventsRecyclerView.layoutManager = layoutManager
            EventsRecyclerView.adapter = rvAdapter
        }
    }

    private object itemClicked : EventsAdapter.onItemClickListener {
        override fun onItemClick(position: Int) {
            // To do
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = EventFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}