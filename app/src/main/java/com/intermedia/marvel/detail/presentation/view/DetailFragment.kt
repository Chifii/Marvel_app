package com.intermedia.marvel.detail.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.intermedia.marvel.R
import com.intermedia.marvel.databinding.DetailFragmentBinding
import com.intermedia.marvel.databinding.EventFragmentBinding
import com.intermedia.marvel.detail.presentation.viewmodel.DetailViewModel
import com.intermedia.marvel.home.presentation.viewmodel.EventViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding
    val model: DetailViewModel by viewModels()

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailFragmentBinding.bind(view)

        // model.detail.observe(viewLifecycleOwner) { setupView(it) }

        model.getDetailData(requireArguments().getInt("characterId"))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}