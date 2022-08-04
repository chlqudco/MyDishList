package com.chlqudco.develop.mydishlist.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.develop.mydishlist.databinding.ActivityMainBinding
import com.chlqudco.develop.mydishlist.presentation.BaseActivity
import com.chlqudco.develop.mydishlist.presentation.adapter.RecordListAdapter
import com.chlqudco.develop.mydishlist.presentation.addrecord.AddRecordActivity
import com.chlqudco.develop.mydishlist.presentation.detail.DetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var adapter: RecordListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {

        //다크모드 금지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //어댑터 초기화
        adapter = RecordListAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("record",it)
            intent.putExtra("uri",it.imageUrl)
            startActivity(intent)
        }

        //리사이클러뷰
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)

        //fab 클릭 리스너
        binding.mainFloatingButton.setOnClickListener {
            val intent = Intent(this,AddRecordActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()

        //목록 가져오기
        CoroutineScope(Dispatchers.Main).launch {
            adapter.recordList = viewModel.getRecordList()
            if (adapter.recordList.size > 0){
                binding.mainNoRecordTextView.isVisible = false
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun observeData() {}

}