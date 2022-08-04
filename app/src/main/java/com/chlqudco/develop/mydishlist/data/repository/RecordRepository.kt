package com.chlqudco.develop.mydishlist.data.repository

import com.chlqudco.develop.mydishlist.data.entity.RecordEntity

interface RecordRepository {

    suspend fun getRecordList(): List<RecordEntity>

    suspend fun addRecord(record: RecordEntity)

    suspend fun getRecordItem(itemId: Long): RecordEntity?

    suspend fun getSearchRecordList(text: String): List<RecordEntity>?
}