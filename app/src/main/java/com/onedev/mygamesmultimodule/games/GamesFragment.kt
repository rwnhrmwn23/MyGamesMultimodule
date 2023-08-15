package com.onedev.mygamesmultimodule.games

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.onedev.mygamesmultimodule.R
import com.onedev.mygamesmultimodule.databinding.FragmentGamesBinding
import com.onedev.mygamesmultimodule.di.viewModelModule
import com.onedev.mygamesmultimodule.utils.BaseFragmentBinding
import com.onedev.mygamesmultimodule.utils.decimalFormat
import com.onedev.mygamesmultimodule.utils.gone
import com.onedev.mygamesmultimodule.utils.visible
import com.onedev.utils.StateEvent
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class GamesFragment : BaseFragmentBinding<FragmentGamesBinding>() {

    private val gamesAdapter by lazy { GamesAdapter() }
    private val gamesViewModel: GamesViewModel by viewModel()
    override fun inflateBinding(container: ViewGroup?): FragmentGamesBinding {
        return FragmentGamesBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(viewModelModule)

        fetchDataGames("")

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                Looper.myLooper()?.let {
                    Handler(it).postDelayed({
                        fetchDataGames(s.toString())
                    }, 1250)
                }
            }
        })

        gamesAdapter.onItemClick = {
            val action = GamesFragmentDirections.actionGamesFragmentToGamesDetailFragment(it.id ?: 0)
            findNavController().navigate(action)
        }
    }

    private fun fetchDataGames(search: String) {
        gamesViewModel.fetchGames(search).observe(requireActivity()) {
            when (it) {
                is StateEvent.Loading -> {
                    binding.rvGames.loadSkeleton(R.layout.layout_item_games_skeleton) {
                        itemCount(5)
                    }
                }

                is StateEvent.Success -> {
                    if (search == "") {
                        val resultCountGame = it.data.count.toString().decimalFormat()
                        binding.edtSearch.hint = getString(R.string.count_found_game, resultCountGame)
                    }

                    binding.rvGames.hideSkeleton()
                    if (it.data.results?.isNotEmpty() == true) {
                        binding.rvGames.adapter = gamesAdapter
                        gamesAdapter.setListData(it.data.results)
                        binding.rvGames.visible()
                        binding.tvNotFound.gone()
                    } else {
                        binding.rvGames.gone()
                        binding.tvNotFound.visible()
                    }
                }

                is StateEvent.Error -> {
                    binding.rvGames.hideSkeleton()
                    binding.rvGames.gone()
                    binding.tvNotFound.visible()
                }
            }
        }
    }
}