package com.akhutornoy.tasteroomencrypted.db

import android.content.Context
import android.text.Editable
import android.text.SpannableStringBuilder
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.commonsware.cwac.saferoom.SafeHelperFactory

@Database(entities = [(User::class)], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        private const val DB_NAME = "Db.db"
        private val encryptionKey: Editable = SpannableStringBuilder("123")
        @Volatile private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .openHelperFactory(SafeHelperFactory.fromUser(encryptionKey))
                .build()
    }
}