package com.otus.groovymovies

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
        const val KEY = "key"
    }

    private var items: ArrayList<FilmItem> = arrayListOf()
    private var favoriteItems: ArrayList<FilmItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inviteFriendButton: View = findViewById(R.id.inviteFriend)
        inviteFriendButton.setOnClickListener {
            inviteFriend()
        }

        val favoriteButton: View = findViewById(R.id.favoriteButton)
        favoriteButton.setOnClickListener {
            val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
            intent.putParcelableArrayListExtra(KEY, favoriteItems)
            startActivity(intent)
        }

        initList()
        initRecycler()
    }

    @Override
    override fun onBackPressed() {
        val dialog: Dialog = CustomDialog(this, object : CustomDialog.DialogClickListener {
            override fun confirmClickListener() {
                finish()
            }

            override fun denyClickListener() {
            }
        })
        dialog.show()
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = FilmAdapter(items, object : FilmAdapter.FilmItemListener {
            override fun onFavoriteClickListener(filmItem: FilmItem) {
                if (!favoriteItems.contains(filmItem)) {
                    favoriteItems.add(filmItem)
                    Toast.makeText(
                        this@MainActivity,
                        "${filmItem.title} " + resources.getString(R.string.add_to_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    favoriteItems.remove(filmItem)
                    Toast.makeText(
                        this@MainActivity,
                        "${filmItem.title} " + resources.getString(R.string.remove_from_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onDetailClickListener(filmItem: FilmItem) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(KEY, filmItem)
                startActivity(intent)
            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == items.size) {
                    itemLoader()
                    recyclerView.adapter?.notifyItemRangeInserted(items.size + 1, 9)
                }
            }
        })

        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecorator)
    }

    private fun inviteFriend() {
        val textMessage = resources.getString(R.string.invite_message)
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
        sendIntent.type = "text/plain"
        val title = resources.getString(R.string.inviteFriend)
        val chooser = Intent.createChooser(sendIntent, title)
        sendIntent.resolveActivity(packageManager)?.let {
            startActivity(chooser)
        }
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

    }

    private fun itemLoader() {
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
}