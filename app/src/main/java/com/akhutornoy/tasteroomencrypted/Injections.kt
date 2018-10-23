package com.akhutornoy.tasteroomencrypted

import android.content.Context
import com.akhutornoy.tasteroomencrypted.db.AppDataBase
import com.akhutornoy.tasteroomencrypted.ui.MainActivityViewModel
import com.akhutornoy.tasteroomencrypted.ui.ViewModelFactory

object Injections {

    fun provideMainActivityViewModel(context: Context) =
        provideViewModelFactory(context)
            .create(MainActivityViewModel::class.java)

    private fun provideUserDataSource(context: Context)= AppDataBase.getInstance(context)

    private fun provideViewModelFactory(context: Context) = ViewModelFactory(provideUserDataSource(context))
}