package com.chlqudco.develop.mydishlist.data.repository

import com.chlqudco.develop.mydishlist.data.db.dao.RecordDao
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RecordRepositoryImpl(
    private val recordDao: RecordDao,
    private val ioDispatcher: CoroutineDispatcher
): RecordRepository {

    override suspend fun getRecordList(): List<RecordEntity> = withContext(ioDispatcher) {
        recordDao.getAll()
    }

    override suspend fun addRecord(record: RecordEntity) = withContext(ioDispatcher) {
        recordDao.insertRecord(record)
    }

    override suspend fun getRecordItem(itemId: Long): RecordEntity? = withContext(ioDispatcher) {
        recordDao.getById(itemId)
    }

}