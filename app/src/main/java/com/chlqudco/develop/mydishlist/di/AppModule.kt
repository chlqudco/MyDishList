package com.chlqudco.develop.mydishlist.di

import com.chlqudco.develop.mydishlist.data.db.provideDB
import com.chlqudco.develop.mydishlist.data.db.provideDao
import com.chlqudco.develop.mydishlist.data.repository.RecordRepository
import com.chlqudco.develop.mydishlist.data.repository.RecordRepositoryImpl
import com.chlqudco.develop.mydishlist.domain.Record.*
import com.chlqudco.develop.mydishlist.presentation.addrecord.AddRecordViewModel
import com.chlqudco.develop.mydishlist.presentation.detail.DetailViewModel
import com.chlqudco.develop.mydishlist.presentation.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module{

    //코루틴 디스패처
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    //뷰모델
    viewModel { MainViewModel(get(), get()) }
    viewModel { AddRecordViewModel(get())}
    viewModel { DetailViewModel(get()) }

    //유스케이스
    factory { GetRecordListUseCase(get()) }
    factory { AddRecordUseCase(get()) }
    factory { GetRecordItemUseCase(get()) }
    factory { GetSearchRecordUseCase(get()) }
    factory { DeleteRecordUseCase(get()) }

    //레포지토리
    single<RecordRepository> { RecordRepositoryImpl(get(),get()) }

    //Room DB
    single { provideDB(androidApplication()) }
    single { provideDao(get()) }

}