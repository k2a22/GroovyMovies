package com.otus.groovymovies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmItem(val title: String, val description: String, val poster: Int) : Parcelable