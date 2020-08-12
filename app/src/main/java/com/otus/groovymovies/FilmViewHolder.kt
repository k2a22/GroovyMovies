package com.otus.groovymovies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTv: TextView = itemView.findViewById(R.id.titleTv)
    private val posterView: ImageView = itemView.findViewById(R.id.posterView)
    private val favoriteView: ImageView = itemView.findViewById(R.id.favoriteView)

    fun bind(filmItem: FilmItem) {
        titleTv.text = filmItem.title
        posterView.setImageResource(filmItem.poster)
        favoriteView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
    }

}
