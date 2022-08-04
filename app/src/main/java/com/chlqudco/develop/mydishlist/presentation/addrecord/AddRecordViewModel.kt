package com.chlqudco.develop.mydishlist.presentation.addrecord

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.domain.Record.AddRecordUseCase
import com.chlqudco.develop.mydishlist.domain.Record.GetRecordItemUseCase
import com.chlqudco.develop.mydishlist.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class AddRecordViewModel(
    private val recordId: Long,
    private val addRecordUseCase: AddRecordUseCase,
    private val getRecordItemUseCase: GetRecordItemUseCase
) : BaseViewModel() {

    private var _addRecordState = MutableLiveData<AddRecordState>(AddRecordState.UnInitialized)
    val addRecordState: LiveData<AddRecordState> = _addRecordState

    private lateinit var recordEntity: RecordEntity

    override fun fetchData(): Job = viewModelScope.launch {
        setState(AddRecordState.Loading)
        getRecordItemUseCase(recordId)?.let { record ->
            recordEntity = record
            setState(AddRecordState.Success(record))
        } ?: kotlin.run {
            setState(AddRecordState.Error)
        }
    }

    fun addRecord(record: RecordEntity) = viewModelScope.launch {
        addRecordUseCase(record)
        setState(AddRecordState.Success(record))
    }

    private fun setState(state: AddRecordState) {
        _addRecordState.postValue(state)
    }

}