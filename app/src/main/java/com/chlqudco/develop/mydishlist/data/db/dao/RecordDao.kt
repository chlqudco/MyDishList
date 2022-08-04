package com.chlqudco.develop.mydishlist.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity

@Dao
interface RecordDao {

    //모든 기록 가져오기
    @Query("SELECT * FROM RecordEntity")
    suspend fun getAll(): List<RecordEntity>

    //기록 하나 저장 하기
    @Insert
    suspend fun insertRecord(recordEntity: RecordEntity)

    //한개 가져오기
    @Query("SELECT * FROM RecordEntity WHERE ID =:id")
    suspend fun getById(id: Long): RecordEntity?

    //검색결과 가져오기
    @Query("SELECT * FROM RecordEntity WHERE title LIKE '%' || :text || '%'")
    suspend fun getByText(text: String): List<RecordEntity>
}