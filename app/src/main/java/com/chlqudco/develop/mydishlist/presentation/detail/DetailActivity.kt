package com.chlqudco.develop.mydishlist.presentation.detail

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.databinding.ActivityDetailBinding
import com.chlqudco.develop.mydishlist.presentation.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

internal class DetailActivity : BaseActivity<DetailViewModel, ActivityDetailBinding>() {

    override val viewModel by viewModel<DetailViewModel>()

    override fun getViewBinding(): ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

    override fun observeData() { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun initViews(){

        //정보 불러와서 대입
        val record = intent.getParcelableExtra<RecordEntity>("record")
        if (record == null){
            finish()
        }
        record?.let {
            val date = Date(record.date)
            val sdf = SimpleDateFormat("yyyy/ MM/ dd")
            binding.detailWriteDateTextView.text = "작성일 : ${sdf.format(date)}"
            binding.detailTitleTextView.text = "제목 : ${record.title}"
            binding.detailReviewTextView.text = "후기 : ${record.review}"
            binding.detailRatingTextView.text = "별점 : ${record.rating.toInt() * 2} / 10 점"
            binding.detailPhotoImageView.setImageURI(Uri.parse(record.imageUrl))
        }

        //나가기 버튼
        binding.detailCloseButton.setOnClickListener {
            finish()
        }

        //삭제 버튼
        binding.detailDeleteButton.setOnClickListener {
            //AlertDialog 띄우기
            showAlertDialog(record!!)
        }
    }

    private fun showAlertDialog(record: RecordEntity) {
        AlertDialog.Builder(this)
            .setTitle("주의")
            .setMessage("정말 삭제하시겠습니까?")
            .setPositiveButton("네") { dialog , _ ->
                viewModel.deleteRecord(record)
                dialog.cancel()
                finish()
            }
            .setNegativeButton("아니오") { _, _ ->  }
            .create()
            .show()
    }
}