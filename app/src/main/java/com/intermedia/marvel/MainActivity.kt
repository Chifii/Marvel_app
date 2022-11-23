package com.intermedia.marvel_challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intermedia.marvel_challenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}