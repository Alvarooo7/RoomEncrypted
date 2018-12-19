package com.akhutornoy.tasteroomencrypted.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int = 1, //I'd like to have only one raw in the table

    val name: String = "",

    val email: String = ""
)
