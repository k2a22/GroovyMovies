package com.otus.groovymovies

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailImage: ImageView = findViewById(R.id.detailImage)
        val detailName: TextView = findViewById(R.id.detailName)
        val detailDescription: TextView = findViewById(R.id.detailDescription)

        val item = intent.getParcelableExtra<FilmItem>(MainActivity.KEY)

        detailImage.setImageResource(item.poster)
        detailName.text = item.title
        detailDescription.text = item.description

    }

}