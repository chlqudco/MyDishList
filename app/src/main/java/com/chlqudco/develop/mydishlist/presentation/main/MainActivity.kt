package com.chlqudco.develop.mydishlist.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import com.chlqudco.develop.mydishlist.databinding.ActivityMainBinding
import com.chlqudco.develop.mydishlist.presentation.BaseActivity
import com.chlqudco.develop.mydishlist.presentation.adapter.RecordListAdapter
import com.chlqudco.develop.mydishlist.presentation.addrecord.AddRecordActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private val adapter = RecordListAdapter()

    private val startAddRecordForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result: ActivityResult ->
            if (result.resultCode == AddRecordActivity.ADD_RECORD_RESULT_CODE){
                viewModel.fetchData()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        //다크모드 금지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //리사이클러뷰
        binding.mainRecyclerView.adapter = adapter

        viewModel.fetchData()

        adapter.recordList = viewModel.recordList
        adapter.notifyDataSetChanged()

        //fab 클릭 리스너
        binding.mainFloatingButton.setOnClickListener {
            val intent = Intent(this,AddRecordActivity::class.java)
            startActivity(intent)
        }
    }

    override fun observeData() {
        viewModel.recordListStateLiveData.observe(this){
            when(it){
                is MainState.UnInitialized -> {
                    initViews()
                }
                is MainState.Loading -> {
                    handleLoadingState()
                }
                is MainState.Success -> {
                    handleSuccessState(it)
                }
                is MainState.Error -> {
                    handleErrorState()
                }
            }
        }
    }

    private fun handleLoadingState(){

    }

    private fun handleSuccessState(state: MainState.Success){
        if (state.RecordList.isEmpty()){
            binding.mainNoRecordTextView.isVisible = true
            binding.mainRecyclerView.isVisible = true
        }
        else{
            binding.mainNoRecordTextView.isVisible = true
            binding.mainRecyclerView.isVisible = true
            adapter.setRecordList(state.RecordList){
                startAddRecordForResult.launch(
                    AddRecordActivity.newIntent(this,it.id)
                )
            }
        }
    }

    private fun handleErrorState(){
        Toast.makeText(this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
    }
}