package com.melhkptn.moviesapp.feature_movie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.melhkptn.moviesapp.R
import dagger.hilt.android.AndroidEntryPoint

//NavHostActivity
@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}