package com.otus.groovymovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteAdapter(
    private val items: List<FilmItem>,
    private val listener: FavoriteAdapter.FavoriteItemListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoriteViewHolder(inflater.inflate(R.layout.item_favorite, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoriteViewHolder) {
            val item = items[position]
            holder.bind(item)
            holder.itemView.deleteViewF.setOnClickListener { listener.onDeleteClickListener(item) }
        }
    }

    interface FavoriteItemListener {
        fun onDeleteClickListener(filmItem: FilmItem)
    }
}