package com.otus.groovymovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class FilmListFragment : Fragment() {
    companion object {
        const val TAG = "FilmListFragment"
    }

    private var items: ArrayList<FilmItem> = arrayListOf()
    private var favoriteItems: ArrayList<FilmItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initList()
        return inflater.inflate(R.layout.fragment_film_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler(view)
    }

    private fun initRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.filmRecyclerView)
        recyclerView.adapter = FilmAdapter(items, object : FilmAdapter.FilmItemListener {
            override fun onFavoriteClickListener(filmItem: FilmItem) {
                if ((activity as? OnFilmClickListener)?.onFavoriteClick(filmItem) == true) {
                    Toast.makeText(
                        requireContext(),
                        "${filmItem.title} " + resources.getString(R.string.add_to_favorites),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    favoriteItems.remove(filmItem)
                    Toast.makeText(
                        requireContext(),
                        "${filmItem.title} " + resources.getString(R.string.already_in_favorites),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onDetailClickListener(filmItem: FilmItem) {
                (activity as? OnFilmClickListener)?.onDetailedClick(filmItem)
            }
        })

        val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecorator)
    }

    private fun initList() {
        items.add(
            FilmItem(
                resources.getString(R.string.shawshank_redemption_1),
                resources.getString(R.string.shawshank_redemption_desc),
                R.drawable.shawshank_redemption
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.green_mile_2),
                resources.getString(R.string.green_mile_desc),
                R.drawable.green_mile
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.forrest_gump_3),
                resources.getString(R.string.forrest_gump_desc),
                R.drawable.forrest_gump
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.schindlers_list_4),
                resources.getString(R.string.schindlers_list_desc),
                R.drawable.schindlers_list
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.intouchables_5),
                resources.getString(R.string.intouchables_desc),
                R.drawable.intouchables
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.inception_6),
                resources.getString(R.string.inception_6),
                R.drawable.inception
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.leon_7),
                resources.getString(R.string.leon_desc),
                R.drawable.leon
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.lion_king_8),
                resources.getString(R.string.lion_king_desc),
                R.drawable.lion_king
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.fight_club_9),
                resources.getString(R.string.fight_club_desc),
                R.drawable.fight_club
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.ivan_vasilievich_10),
                resources.getString(R.string.ivan_vasilievich_desc),
                R.drawable.ivan_vasilievich
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.la_vita_è_bella_11),
                resources.getString(R.string.la_vita_è_bella_desc),
                R.drawable.la_vita_e_bella
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.knockin_on_heavens_door_12),
                resources.getString(R.string.knockin_on_heavens_door_desc),
                R.drawable.knockin_on_heavens_door
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.godfather_13),
                resources.getString(R.string.godfather_desc),
                R.drawable.godfather
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.pulp_fiction_14),
                resources.getString(R.string.pulp_fiction_desc),
                R.drawable.pulp_fiction
            )
        )
        items.add(
            FilmItem(
                resources.getString(R.string.operation_y_15),
                resources.getString(R.string.operation_y_desc),
                R.drawable.operation_y
            )
        )

    }

    interface OnFilmClickListener {
        fun onDetailedClick(filmItem: FilmItem)
        fun onFavoriteClick(filmItem: FilmItem): Boolean
    }
}