package com.chlqudco.develop.mydishlist.data.db

import android.content.Context
import androidx.room.Room

internal fun provideDB(context: Context): RecordDatabase{
    return Room.databaseBuilder(context, RecordDatabase::class.java, RecordDatabase.DB_NAME).build()
}

internal fun provideDao(database: RecordDatabase) = database.RecordDao()