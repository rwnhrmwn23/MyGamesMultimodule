package com.onedev.mygamesmultimodule.games

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedev.data.model.Games
import com.onedev.mygamesmultimodule.databinding.LayoutItemGamesBinding

class GamesAdapter :
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private var datas = ArrayList<Games.Result>()
    var onItemClick: ((Games.Result) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(list: List<Games.Result>?) {
        if (list == null) return
        datas.clear()
        datas.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutItemGamesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Games.Result) {
            binding.games = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutItemGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}