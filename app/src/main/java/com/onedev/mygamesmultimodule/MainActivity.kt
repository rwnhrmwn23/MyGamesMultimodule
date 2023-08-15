package com.onedev.mygamesmultimodule

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.onedev.mygamesmultimodule.databinding.ActivityMainBinding
import com.onedev.mygamesmultimodule.utils.BaseActivityBinding
import com.onedev.mygamesmultimodule.utils.gone
import com.onedev.mygamesmultimodule.utils.visible

class MainActivity : BaseActivityBinding<ActivityMainBinding>() {

    override fun inflateBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        val navController = findNavController(R.id.menuBottomNavigationFragment)
        binding.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.gamesFragment, R.id.favoriteFragment -> {
                    binding.bottomNavigationView.visible()
                }
                else -> {
                    binding.bottomNavigationView.gone()
                }
            }
        }
    }
}