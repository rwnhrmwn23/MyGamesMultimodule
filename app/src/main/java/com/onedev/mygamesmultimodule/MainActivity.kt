package com.onedev.mygamesmultimodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onedev.mygamesmultimodule.databinding.ActivityMainBinding
import com.onedev.network.utils.StateEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val gamesViewModel: GamesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gamesViewModel.fetchGames().observe(this@MainActivity) {
            when (it) {
                is StateEvent.Loading -> {
                    binding.tvTest.text = "Loading"
                }
                is StateEvent.Success -> {
                    binding.tvTest.text = it.data.toString()
                }
                is StateEvent.Error -> {
                    binding.tvTest.text = it.exception
                }
            }
        }
    }
}