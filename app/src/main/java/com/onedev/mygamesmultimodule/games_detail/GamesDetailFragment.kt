package com.onedev.mygamesmultimodule.games_detail

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.onedev.data.model.Games
import com.onedev.mygamesmultimodule.R
import com.onedev.mygamesmultimodule.databinding.FragmentGamesDetailBinding
import com.onedev.mygamesmultimodule.di.viewModelModule
import com.onedev.mygamesmultimodule.utils.BaseFragmentBinding
import com.onedev.mygamesmultimodule.utils.showToast
import com.onedev.utils.StateEvent
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class GamesDetailFragment : BaseFragmentBinding<FragmentGamesDetailBinding>(),
    View.OnClickListener {

    private val gamesDetailViewModel: GamesDetailViewModel by viewModel()
    private val args: GamesDetailFragmentArgs by navArgs()
    private var games: Games.Result? = null
    private var isFavorite = false

    override fun inflateBinding(container: ViewGroup?): FragmentGamesDetailBinding {
        return FragmentGamesDetailBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(viewModelModule)

        fetchDetailGame()

        binding.imgBackButton.setOnClickListener(this)
        binding.imgFavoriteButton.setOnClickListener(this)
    }

    private fun fetchDetailGame() {
        gamesDetailViewModel.checkGameIsFavorite(args.gamesId).observe(requireActivity()) { game ->
            if (game.id != null) {
                isFavorite = true
                binding.imgFavoriteButton.setImageResource(R.drawable.ic_round_favorite)
            } else {
                isFavorite = false
                binding.imgFavoriteButton.setImageResource(R.drawable.ic_round_favorite_border)
            }
        }

        gamesDetailViewModel.fetchDetailGames(args.gamesId).observe(requireActivity()) {
            when (it) {
                is StateEvent.Loading -> {
                    binding.imgBackground.loadSkeleton()
                    binding.tvPublisher.loadSkeleton(length = 15)
                    binding.tvName.loadSkeleton(length = 25)
                    binding.tvReleasedDate.loadSkeleton(length = 15)
                    binding.tvDescription.loadSkeleton(length = 30)
                }

                is StateEvent.Success -> {
                    hideSkeleton()

                    it.data.let {data ->
                        binding.games = data
                        binding.executePendingBindings()

                        games = Games.Result(
                            backgroundImage = data.backgroundImage,
                            id = data.id,
                            name = data.name,
                            rating = data.rating,
                            released = data.released,
                        )
                    }
                }

                is StateEvent.Error -> {
                    hideSkeleton()
                }
            }
        }
    }

    private fun hideSkeleton() {
        binding.imgBackground.hideSkeleton()
        binding.tvPublisher.hideSkeleton()
        binding.tvName.hideSkeleton()
        binding.tvReleasedDate.hideSkeleton()
        binding.tvDescription.hideSkeleton()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.imgBackButton -> {
                findNavController().navigateUp()
            }
            binding.imgFavoriteButton -> {
                games?.let {
                    if (isFavorite) {
                        gamesDetailViewModel.deleteGames(it)
                        binding.imgFavoriteButton.setImageResource(R.drawable.ic_round_favorite_border)
                        requireActivity().showToast(getString(R.string.success_delete_to_favorite))
                    } else {
                        gamesDetailViewModel.insertGames(it)
                        binding.imgFavoriteButton.setImageResource(R.drawable.ic_round_favorite)
                        requireActivity().showToast(getString(R.string.success_add_to_favorite))
                    }

                    isFavorite = !isFavorite
                }
            }
        }
    }

}