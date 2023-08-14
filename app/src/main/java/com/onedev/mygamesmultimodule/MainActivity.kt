package com.onedev.mygamesmultimodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onedev.data.model.Games
import com.onedev.mygamesmultimodule.databinding.ActivityMainBinding
import com.onedev.utils.StateEvent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val gamesViewModel: GamesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(viewModelModule)

        gamesViewModel.fetchGames().observe(this@MainActivity) {
            when (it) {
                is StateEvent.Loading -> {
                    binding.tvTest.text = "Loading"
                }

                is StateEvent.Success -> {
                    binding.tvTest.text = it.data.count.toString()
                    val data = Games.Result(
                        background_image = it.data.results?.get(1)?.background_image ?: "-",
                        id = it.data.results?.get(1)?.id ?: 0,
                        name = it.data.results?.get(1)?.name ?: "-",
                        rating = it.data.results?.get(1)?.rating ?: 0.0,
                        released = it.data.results?.get(1)?.released ?: "-",
                        short_screenshots = it.data.results?.get(1)?.short_screenshots ?: "-"
                    )
                    gamesViewModel.addUser(data)
                }

                is StateEvent.Error -> {
                    binding.tvTest.text = it.exception
                }
            }
        }
    }
}