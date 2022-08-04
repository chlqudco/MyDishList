package com.chlqudco.develop.mydishlist.presentation.addrecord

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.databinding.ActivityAddRecordBinding
import com.chlqudco.develop.mydishlist.presentation.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class AddRecordActivity : BaseActivity<AddRecordViewModel, ActivityAddRecordBinding>() {

    override val viewModel by viewModel<AddRecordViewModel>{
        parametersOf(intent.getLongExtra(RECORD_ID_KEY, -1))
    }

    override fun getViewBinding(): ActivityAddRecordBinding = ActivityAddRecordBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.addRecordState.observe(this) {
        when(it){
            is AddRecordState.UnInitialized -> initViews()
            is AddRecordState.Loading -> handleLoading()
            is AddRecordState.Add -> handleAdd()
            is AddRecordState.Error -> handleError()
            is AddRecordState.Success -> handleSuccess(it)
        }
    }

    private fun initViews() {
        binding.addRecordSaveButton.setOnClickListener {
            val title = binding.addRecordTitleEditText.text.toString()
            val rating = binding.addRecordRatingBar.rating
            val record = RecordEntity(0, title ,rating)
            viewModel.addRecord(record)
            Toast.makeText(this,"추가 되었습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleLoading(){

    }

    private fun handleSuccess(state: AddRecordState.Success){

    }

    private fun handleError(){

    }

    private fun handleAdd(){
        setResult(ADD_RECORD_RESULT_CODE)
        finish()
    }

    companion object{

        const val RECORD_ID_KEY = "RECORD_ID_KEY"

        const val ADD_RECORD_RESULT_CODE = 99

        fun newIntent(context: Context, recordId: Long) = Intent(context, AddRecordActivity::class.java).apply{
            putExtra(RECORD_ID_KEY, recordId)
        }
    }

}