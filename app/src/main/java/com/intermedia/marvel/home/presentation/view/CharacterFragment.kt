package com.intermedia.marvel.home.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.marvel.databinding.CharacterFragmentBinding
import com.intermedia.marvel.detail.presentation.view.DetailActivity
import com.intermedia.marvel.home.domain.models.ResultsModel
import com.intermedia.marvel.home.presentation.view.adapters.CharacterAdapter
import com.intermedia.marvel.home.presentation.viewmodel.CharacterViewModel

class CharacterFragment : Fragment() {
    private lateinit var binding: CharacterFragmentBinding
    val model: CharacterViewModel by viewModels()
    private var characters: MutableList<ResultsModel> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CharacterFragmentBinding.bind(view)

        model.characters.observe(viewLifecycleOwner) { setupView(it) }
        model.characterId.observe(viewLifecycleOwner) { goToDetail(it) }
        model.moreCharacters.observe(viewLifecycleOwner) { moreCharacters(it) }

        model.getCharactersData()

    }

    private fun setupView(data: List<ResultsModel>) {
        populateList(data)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        val rvAdapter = CharacterAdapter(characters)

        binding.apply {
            CharacterRecyclerView.layoutManager = layoutManager
            CharacterRecyclerView.adapter = rvAdapter
            CharacterRecyclerView.setHasFixedSize(true)
        }

        binding.CharacterRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    model.getMoreCharacters(15)
                }
            }
        })

        rvAdapter.setOnClickListener(
            object : CharacterAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    model.getCharacterId(position)
                }
            }
        )
    }

    private fun moreCharacters(data: List<ResultsModel>) {
        populateList(data)
        binding.CharacterRecyclerView.adapter?.notifyItemRangeInserted(
            characters.size - 15,
            characters.size
        )
    }

    private fun populateList(data: List<ResultsModel>) {
        data.forEach { characters.add(it) }
    }

    private fun goToDetail(characterId: Int) {
        val intent = Intent(this.context, DetailActivity::class.java)
        intent.putExtra("characterId", characterId)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}