package com.onedev.mygamesmultimodule.favorite

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.onedev.mygamesmultimodule.R
import com.onedev.mygamesmultimodule.databinding.FragmentFavoriteBinding
import com.onedev.mygamesmultimodule.di.viewModelModule
import com.onedev.mygamesmultimodule.games.GamesAdapter
import com.onedev.mygamesmultimodule.games.GamesFragmentDirections
import com.onedev.mygamesmultimodule.games.GamesViewModel
import com.onedev.mygamesmultimodule.utils.BaseFragmentBinding
import com.onedev.mygamesmultimodule.utils.decimalFormat
import com.onedev.mygamesmultimodule.utils.gone
import com.onedev.mygamesmultimodule.utils.visible
import com.onedev.utils.StateEvent
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : BaseFragmentBinding<FragmentFavoriteBinding>() {

    private val favoriteAdapter by lazy { FavoriteAdapter() }
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    override fun inflateBinding(container: ViewGroup?): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(viewModelModule)

        fetchFavoriteGames()

        favoriteAdapter.onItemClick = {
            val action = FavoriteFragmentDirections.actionFavoriteFragment2ToGamesDetailFragment(it.id ?: 0)
            findNavController().navigate(action)
        }
    }

    private fun fetchFavoriteGames() {
        favoriteViewModel.fetchFavoriteGames().observe(requireActivity()) {
            if (it.results?.isNotEmpty() == true) {
                binding.rvGames.adapter = favoriteAdapter
                favoriteAdapter.setListData(it.results)
                binding.rvGames.visible()
                binding.tvNotFound.gone()
            } else {
                binding.rvGames.gone()
                binding.tvNotFound.visible()
            }
        }
    }
}