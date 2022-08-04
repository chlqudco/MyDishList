package com.chlqudco.develop.mydishlist.presentation.detail

import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.domain.Record.DeleteRecordUseCase
import com.chlqudco.develop.mydishlist.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class DetailViewModel(
    private val deleteRecordUseCase: DeleteRecordUseCase
): BaseViewModel() {
    override fun fetchData(): Job = Job()

    fun deleteRecord(record: RecordEntity) = viewModelScope.launch{
        deleteRecordUseCase(record)
    }
}