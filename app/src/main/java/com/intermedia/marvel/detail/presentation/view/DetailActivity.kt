package com.intermedia.marvel.detail.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intermedia.marvel.databinding.DetailFragmentBinding
import com.intermedia.marvel.detail.presentation.viewmodel.DetailViewModel
import com.intermedia.marvel.home.domain.models.ResultsModel
import com.intermedia.marvel.home.presentation.view.adapters.DetailAdapter

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: DetailFragmentBinding
    private var characterId = 0

    val model: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent

        characterId = intent.getIntExtra("characterId", 0)

        model.detail.observe(this) { setupView(it) }

        // model.getDetailData(requireArguments().getInt("characterId"))
        model.getDetailData(characterId)
    }

    fun setupView(data: ResultsModel) {
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val rvAdapter = data.comics?.items?.let { DetailAdapter(it) }

        binding.apply {
            detailTitle.text = data.name
            detailDescription.text = data.description

            val imgUrl = data.thumbnail?.path + "." + data.thumbnail?.extension

            Glide.with(this.root)
                .load(imgUrl)
                .centerCrop()
                .into(detailImageView)

            detailRecyclerView.layoutManager = layoutManager
            detailRecyclerView.adapter = rvAdapter
            detailRecyclerView.isNestedScrollingEnabled = false

            detailCloseButton.setOnClickListener {
                super.onBackPressed()
            }
        }

        rvAdapter?.setOnClickListener(
            object : DetailAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    // For now it's not necessary
                }

            }
        )
    }
}