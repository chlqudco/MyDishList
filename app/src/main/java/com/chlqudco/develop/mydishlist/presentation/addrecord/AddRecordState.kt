package com.chlqudco.develop.mydishlist.presentation.addrecord

import com.chlqudco.develop.mydishlist.data.entity.RecordEntity

sealed class AddRecordState {

    object UnInitialized: AddRecordState()

    object Loading: AddRecordState()

    data class Success(
        val RecordList: RecordEntity
    ): AddRecordState()

    object Add: AddRecordState()

    object Error: AddRecordState()
}