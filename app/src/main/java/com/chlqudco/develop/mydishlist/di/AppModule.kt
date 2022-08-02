package com.chlqudco.develop.mydishlist.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

internal val appModule = module{

    //코루틴 디스패처
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    //뷰모델


    //유스케이스


    //레포지토리


    //Room DB


}