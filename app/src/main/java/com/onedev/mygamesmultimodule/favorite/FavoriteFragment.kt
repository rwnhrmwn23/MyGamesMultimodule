package com.onedev.mygamesmultimodule.favorite

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.onedev.mygamesmultimodule.databinding.FragmentFavoriteBinding
import com.onedev.mygamesmultimodule.utils.BaseFragmentBinding

class FavoriteFragment : BaseFragmentBinding<FragmentFavoriteBinding>() {
    override fun inflateBinding(container: ViewGroup?): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}