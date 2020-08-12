package com.otus.groovymovies

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View

class CustomDialog(context: Context, private val listener: CustomDialog.DialogClickListener) :
    Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)
        val confirmButton = findViewById<View>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            listener.confirmClickListener()
        }
        val denyButton = findViewById<View>(R.id.denyButton)
        denyButton.setOnClickListener {
            listener.denyClickListener()
            dismiss()
        }
    }

    interface DialogClickListener {
        fun confirmClickListener()
        fun denyClickListener()
    }
}