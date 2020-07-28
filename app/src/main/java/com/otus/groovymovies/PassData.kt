package com.otus.groovymovies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PassData (val description: String) : Parcelable {
    override fun toString(): String {
        return description
    }
}