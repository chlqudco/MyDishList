package com.chlqudco.develop.mydishlist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chlqudco.develop.mydishlist.data.db.dao.RecordDao
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity

@Database(entities = [RecordEntity::class], version = 1, exportSchema = false)
abstract class RecordDatabase: RoomDatabase() {
    abstract fun RecordDao(): RecordDao

    companion object{
        const val DB_NAME = "RecordDataBase.db"
    }
}