package com.chlqudco.develop.mydishlist.presentation.addrecord

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.domain.Record.AddRecordUseCase
import com.chlqudco.develop.mydishlist.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class AddRecordViewModel(
    private val addRecordUseCase: AddRecordUseCase,
) : BaseViewModel() {

    override fun fetchData(): Job = viewModelScope.launch {}

    fun addRecord(record: RecordEntity) = viewModelScope.launch {
        addRecordUseCase(record)
    }

}