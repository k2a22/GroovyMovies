package com.otus.groovymovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_film.view.*

class FilmAdapter(
    private val items: List<FilmItem>,
    private val listener: FilmItemListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_FOOTER = 1
        const val VIEW_TYPE_ITEM = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_ITEM) {
            FilmViewHolder(inflater.inflate(R.layout.item_film, parent, false))
        } else {
            FooterViewHolder(inflater.inflate(R.layout.item_film_footer, parent, false))
        }

    }

    override fun getItemCount() = items.size + 1 // +1 footer

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size) VIEW_TYPE_FOOTER else VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FilmViewHolder) {
            val item = items[position]
            holder.bind(item)
            holder.itemView.favoriteView.setOnClickListener { listener.onFavoriteClickListener(item) }
            holder.itemView.detailButton.setOnClickListener { listener.onDetailClickListener(item) }
        }
    }

    interface FilmItemListener {
        fun onFavoriteClickListener(filmItem: FilmItem)
        fun onDetailClickListener(filmItem: FilmItem)
    }

}
