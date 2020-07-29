package com.otus.groovymovies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private var isLiked: String = ""
    private var comment: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailImage: ImageView = findViewById(R.id.detailImage)
        val detailName: TextView = findViewById(R.id.detailName)
        val detailDescription: TextView = findViewById(R.id.detailDescription)

        when (intent.getParcelableExtra<PassData>(MainActivity.KEY).toString()) {
            "tsr" -> {
                detailImage.setImageResource(R.drawable.tsr)
                detailName.setText(R.string.tsr_name)
                detailDescription.setText(R.string.tsr_description)
            }
            "gm" -> {
                detailImage.setImageResource(R.drawable.gm)
                detailName.setText(R.string.gm_name)
                detailDescription.setText(R.string.gm_description)
            }
            "fg" -> {
                detailImage.setImageResource(R.drawable.fg)
                detailName.setText(R.string.fg_name)
                detailDescription.setText(R.string.fg_description)
            }
        }

        val likeCheckBox: CheckBox = findViewById(R.id.likeCheckBox)
        likeCheckBox.setOnClickListener {
            isLiked = if (likeCheckBox.isChecked) {
                "true"
            } else {
                "false"
            }
        }

        val commentText: EditText = findViewById(R.id.commentText)
        commentText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                comment = s.toString()
            }

        })

        val intent = Intent()
        intent.putExtra(MainActivity.COMMENT, comment)
        intent.putExtra(MainActivity.LIKE, isLiked)
        setResult(Activity.RESULT_OK, intent)
    }
}