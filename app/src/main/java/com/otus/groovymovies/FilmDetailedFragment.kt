package com.otus.groovymovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class FilmDetailedFragment : Fragment() {
    companion object {
        const val TAG = "FilmDetailedFragment"
        const val KEY = "detail"

        fun newInstance(filmItem: FilmItem): FilmDetailedFragment {
            val fragment = FilmDetailedFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY, filmItem)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val filmItem = arguments?.get(KEY) as FilmItem
        view.findViewById<TextView>(R.id.detailDescription).text = filmItem.description
        view.findViewById<ImageView>(R.id.detailImage).setImageResource(filmItem.poster)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = filmItem.title
    }
}