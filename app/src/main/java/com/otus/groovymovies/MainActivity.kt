package com.otus.groovymovies

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
        const val KEY = "key"
        const val COMMENT = "comment"
        const val LIKE = "like"
        const val REQUEST_CODE = 1
    }

    private lateinit var tsrName: TextView
    private lateinit var gmName: TextView
    private lateinit var fgName: TextView

    private var chosenFilm: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tsrName = findViewById(R.id.tsrName)
        gmName = findViewById(R.id.gmName)
        fgName = findViewById(R.id.fgName)

        val tsrButton: Button = findViewById(R.id.tsrDetail)
        tsrButton.setOnClickListener {
            chosenFilm = "tsr"
            chooseFilm(chosenFilm)
            getDetail(chosenFilm)
        }
        val gmButton: Button = findViewById(R.id.gmDetail)
        gmButton.setOnClickListener {
            chosenFilm = "gm"
            chooseFilm(chosenFilm)
            getDetail(chosenFilm)
        }
        val fgButton: Button = findViewById(R.id.fgDetail)
        fgButton.setOnClickListener {
            chosenFilm = "fg"
            chooseFilm(chosenFilm)
            getDetail(chosenFilm)
        }

        val inviteFriendButton: Button = findViewById(R.id.inviteFriend)
        inviteFriendButton.setOnClickListener {
            inviteFriend()
        }

        chooseFilm(chosenFilm)

        savedInstanceState?.apply {
            chosenFilm = this.getString(KEY, "")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        chosenFilm = savedInstanceState.getString(KEY, "")
        chooseFilm(chosenFilm)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY, chosenFilm)
    }

    private fun getDetail(filmName: String) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(KEY, PassData(filmName))
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            var comment: String? = null
            var isLiked: String? = null
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    comment = it.getStringExtra(COMMENT)
                    isLiked = it.getStringExtra(LIKE)
                }
            }
            Log.d(TAG,"Comment text: $comment")
            Log.d(TAG,"Like checkbox is $isLiked")
        }
    }

    private fun chooseFilm(filmName: String) {
        when (filmName) {
            "tsr" -> {
                tsrName.setTextColor(Color.BLUE)
                gmName.setTextColor(Color.BLACK)
                fgName.setTextColor(Color.BLACK)
            }
            "gm" -> {
                tsrName.setTextColor(Color.BLACK)
                gmName.setTextColor(Color.BLUE)
                fgName.setTextColor(Color.BLACK)
            }
            "fg" -> {
                tsrName.setTextColor(Color.BLACK)
                gmName.setTextColor(Color.BLACK)
                fgName.setTextColor(Color.BLUE)
            }
            else -> {
                tsrName.setTextColor(Color.BLACK)
                gmName.setTextColor(Color.BLACK)
                fgName.setTextColor(Color.BLACK)
            }
        }
    }

    private fun inviteFriend() {
        val textMessage = "Воспользуйся приложением Groovy Movies!"
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
}