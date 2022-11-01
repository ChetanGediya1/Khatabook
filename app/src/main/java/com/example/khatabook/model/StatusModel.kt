package com.example.khatabook.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatusModel(
    val id: String,
    val name: String,
    val mobile: String,



    ):Parcelable
