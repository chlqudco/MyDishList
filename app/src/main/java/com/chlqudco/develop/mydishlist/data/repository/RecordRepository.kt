package com.chlqudco.develop.mydishlist.data.repository

import com.chlqudco.develop.mydishlist.data.entity.RecordEntity

interface RecordRepository {

    suspend fun getRecordList(): List<RecordEntity>
}