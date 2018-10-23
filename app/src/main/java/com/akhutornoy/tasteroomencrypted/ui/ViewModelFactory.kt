package com.akhutornoy.tasteroomencrypted.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akhutornoy.tasteroomencrypted.db.AppDataBase

class ViewModelFactory(private val dataBase: AppDataBase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) ->
                MainActivityViewModel(dataBase.getUserDao())
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}