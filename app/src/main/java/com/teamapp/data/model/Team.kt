package com.teamapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    val name: String,
    val captain: Boolean
): Parcelable
