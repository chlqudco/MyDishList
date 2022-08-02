package com.chlqudco.develop.mydishlist.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity

@Dao
interface RecordDao {

    @Query("SELECT * FROM RecordEntity")
    suspend fun getAll(): List<RecordEntity>
}