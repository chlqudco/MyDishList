package com.chlqudco.develop.mydishlist.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity

@Dao
interface RecordDao {

    @Query("SELECT * FROM RecordEntity")
    suspend fun getAll(): List<RecordEntity>

    @Insert
    suspend fun insertRecord(recordEntity: RecordEntity)

    @Query("SELECT * FROM RecordEntity WHERE ID =:id")
    suspend fun getById(id: Long): RecordEntity?
}