package com.chlqudco.develop.mydishlist.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.chlqudco.develop.mydishlist.databinding.ActivityMainBinding
import com.chlqudco.develop.mydishlist.presentation.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        //다크모드 금지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun observeData() {

    }
}