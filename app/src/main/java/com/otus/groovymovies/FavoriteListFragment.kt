package com.otus.groovymovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class FavoriteListFragment : Fragment() {
    companion object {
        const val TAG = "FavoriteListFragment"
        private const val KEY = "favorites"

        fun newInstance(items: ArrayList<FilmItem>): FavoriteListFragment {
            val fragment = FavoriteListFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(KEY, items)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var items: ArrayList<FilmItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initList()
        initRecycler(view)
    }

    private fun initList() {
        items = arguments?.getParcelableArrayList<FilmItem>(KEY) as ArrayList<FilmItem>
    }

    private fun initRecycler(view: View) {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewFv)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter =
            FavoriteAdapter(items, object : FavoriteAdapter.FavoriteItemListener {
                override fun onDeleteClickListener(filmItem: FilmItem) {
                    val index = items.indexOf(filmItem)
                    items.remove(filmItem)
                    recyclerView.adapter?.notifyItemRemoved(index)
                    val listener = View.OnClickListener {
                        items.add(index, filmItem)
                        recyclerView.adapter?.notifyItemInserted(index)
                    }
                    Snackbar
                        .make(
                            view,
                            filmItem.title + " " + resources.getString(R.string.remove_question),
                            Snackbar.LENGTH_SHORT
                        )
                        .setAction(resources.getString(R.string.undo), listener)
                        .show()
                }
            })

        val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecorator)
    }
}