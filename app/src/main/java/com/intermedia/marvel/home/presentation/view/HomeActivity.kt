package com.intermedia.marvel.home.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.marvel.R
import com.intermedia.marvel.databinding.ActivityHomeBinding
import com.intermedia.marvel.home.domain.models.ResultsModel
import com.intermedia.marvel.home.presentation.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    val model: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigationView.itemIconTintList = null

        binding.bottomNavigationView.setOnClickListener {
            when (it.id) {
                R.id.navigation_characters -> {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_characterFragment_to_eventFragment2)
                }
                R.id.navigation_events -> {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_eventFragment_to_characterFragment2)
                }
            }
        }
    }

}