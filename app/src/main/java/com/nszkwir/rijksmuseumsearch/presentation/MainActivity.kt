package com.nszkwir.rijksmuseumsearch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nszkwir.rijksmuseumsearch.R
import com.nszkwir.rijksmuseumsearch.databinding.MainActivityBinding
import com.nszkwir.rijksmuseumsearch.presentation.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun showProgressBar() {
        binding.clProgressBar.visibility = View.VISIBLE
        binding.coordinator.alpha = DIM_BACKGROUND_ALPHA
    }

    fun hideProgressBar() {
        binding.clProgressBar.visibility = View.GONE
        binding.coordinator.alpha = VISIBLE_BACKGROUND_ALPHA
    }

    companion object {
        const val DIM_BACKGROUND_ALPHA = 0.75f
        const val VISIBLE_BACKGROUND_ALPHA = 1f
    }
}