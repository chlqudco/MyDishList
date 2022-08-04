package com.chlqudco.develop.mydishlist.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.domain.Record.GetRecordListUseCase
import com.chlqudco.develop.mydishlist.domain.Record.GetSearchRecordUseCase
import com.chlqudco.develop.mydishlist.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class MainViewModel(
    val getRecordListUseCase: GetRecordListUseCase,
    val getSearchRecordUseCase: GetSearchRecordUseCase
): BaseViewModel() {

    var recordList: List<RecordEntity> = mutableListOf()

    //이게 맞아??
    override fun fetchData(): Job = viewModelScope.launch{
        recordList = getRecordListUseCase()
    }

    suspend fun getRecordList(): List<RecordEntity>{
        return getRecordListUseCase()
    }

    suspend fun getSearchList(text: String): List<RecordEntity>? {
        return getSearchRecordUseCase(text)
    }

}