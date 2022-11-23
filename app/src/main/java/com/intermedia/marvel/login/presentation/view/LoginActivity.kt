package com.intermedia.marvel.login.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.intermedia.marvel.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }
}