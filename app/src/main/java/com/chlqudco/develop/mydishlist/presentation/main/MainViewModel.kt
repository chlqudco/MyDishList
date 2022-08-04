package com.chlqudco.develop.mydishlist.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.domain.Record.GetRecordListUseCase
import com.chlqudco.develop.mydishlist.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class MainViewModel(
    val getRecordListUseCase: GetRecordListUseCase
): BaseViewModel() {

    private val _recordListStateLiveData = MutableLiveData<MainState>(MainState.UnInitialized)
    val recordListStateLiveData: LiveData<MainState> = _recordListStateLiveData
    var recordList: List<RecordEntity> = mutableListOf()

    //이게 맞아??
    override fun fetchData(): Job = viewModelScope.launch{
        _recordListStateLiveData.postValue(MainState.Loading)
        _recordListStateLiveData.postValue(MainState.Success(getRecordListUseCase()))
        recordList = getRecordListUseCase()
    }

}