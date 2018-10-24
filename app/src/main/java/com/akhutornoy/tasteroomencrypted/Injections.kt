package com.akhutornoy.tasteroomencrypted

import android.content.Context
import com.akhutornoy.tasteroomencrypted.db.AppDataBase
import com.akhutornoy.tasteroomencrypted.ui.saveloaduser.SaveLoadUserActivityViewModel
import com.akhutornoy.tasteroomencrypted.ui.saveloaduser.ViewModelFactory

object Injections {

    fun provideMainActivityViewModel(context: Context) =
        provideViewModelFactory(context)
            .create(SaveLoadUserActivityViewModel::class.java)

    private fun provideUserDataSource(context: Context)= AppDataBase.getInstance(context)

    private fun provideViewModelFactory(context: Context) =
        ViewModelFactory(provideUserDataSource(context))
}