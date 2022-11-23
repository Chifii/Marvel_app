package com.intermedia.marvel.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.marvel.R
import com.intermedia.marvel.databinding.CharacterFragmentBinding
import com.intermedia.marvel.home.domain.models.ResultsModel
import com.intermedia.marvel.home.presentation.view.adapters.CharacterAdapter
import com.intermedia.marvel.home.presentation.viewmodel.CharacterViewModel

class CharacterFragment : Fragment() {
    private lateinit var binding: CharacterFragmentBinding
    val model: CharacterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CharacterFragmentBinding.bind(view)

        model.characters.observe(viewLifecycleOwner) { setupView(it) }
        model.characterId.observe(viewLifecycleOwner) { goToDetail(it) }

        model.getCharactersData()

    }

    private fun setupView(data: List<ResultsModel>) {
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        val rvAdapter = CharacterAdapter(data)

        binding.apply {
            CharacterRecyclerView.layoutManager = layoutManager
            CharacterRecyclerView.adapter = rvAdapter
        }

        rvAdapter.setOnClickListener(
            object : CharacterAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    model.getCharacterId(position)
                }

            }
        )
    }

    private fun goToDetail(characterId: Int) {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_characterFragment_to_detailFragment, Bundle().apply {
                putInt("characterId", characterId)
            })
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