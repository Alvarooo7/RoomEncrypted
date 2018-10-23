package com.akhutornoy.tasteroomencrypted.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val name: String = "",

    val email: String = ""
)
