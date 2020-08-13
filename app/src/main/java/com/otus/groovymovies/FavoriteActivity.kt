package com.otus.groovymovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoriteActivity : AppCompatActivity() {

    private lateinit var items: ArrayList<FilmItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        initList()
        initRecycler()
    }

    private fun initList() {
        items = intent.getParcelableArrayListExtra(MainActivity.KEY)
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewF)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter =
            FavoriteAdapter(items, object : FavoriteAdapter.FavoriteItemListener {
                override fun onDeleteClickListener(item: FilmItem) {
                    val index = items.indexOf(item)
                    items.remove(item)
                    recyclerView.adapter?.notifyItemRemoved(index)
                }
            })

        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecorator)
    }
}