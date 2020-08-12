package com.otus.groovymovies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTv: TextView = itemView.findViewById(R.id.titleTvF)
    private val posterView: ImageView = itemView.findViewById(R.id.posterViewF)

    fun bind(filmItem: FilmItem) {
        titleTv.text = filmItem.title
        posterView.setImageResource(filmItem.poster)
    }
}