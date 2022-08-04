package com.chlqudco.develop.mydishlist.domain.Record

import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.data.repository.RecordRepository
import com.chlqudco.develop.mydishlist.domain.UseCase

class DeleteRecordUseCase(
    private val recordRepository: RecordRepository
):UseCase {

    suspend operator fun invoke(record: RecordEntity){
        recordRepository.deleteRecordById(record)
    }
}