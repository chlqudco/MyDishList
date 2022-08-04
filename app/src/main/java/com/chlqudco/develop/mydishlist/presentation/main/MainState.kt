package com.chlqudco.develop.mydishlist.presentation.main

import com.chlqudco.develop.mydishlist.data.entity.RecordEntity

internal sealed class MainState {

    object UnInitialized: MainState()

    object Loading: MainState()

    data class Success(
        val RecordList: List<RecordEntity>
    ): MainState()

    object Error: MainState()
}