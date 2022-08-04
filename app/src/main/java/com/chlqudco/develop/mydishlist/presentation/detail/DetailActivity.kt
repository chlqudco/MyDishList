package com.chlqudco.develop.mydishlist.presentation.detail

import android.Manifest
import android.annotation.SuppressLint
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
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 10002)
        }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun initViews(){

        //이게 맞나? ㄷㄷ;;
        val record = intent.getParcelableExtra<RecordEntity>("record")
        val imageUrl = intent.getStringExtra("uri")

        record?.let {
            val date = Date(record.date)
            val sdf = SimpleDateFormat("yyyy/ MM/ dd")
            binding.detailWriteDateTextView.text = "작성일 : ${sdf.format(date)}"
            binding.detailTitleTextView.text = "제목 : ${record.title}"
            binding.detailReviewTextView.text = "후기 : ${record.review}"
            binding.detailRatingTextView.text = "별점 : ${record.rating.toInt() * 2} / 10 점"
        }

        binding.detailPhotoImageView.setImageURI(null)
        binding.detailPhotoImageView.setImageURI(Uri.parse(imageUrl))


        binding.detailCloseButton.setOnClickListener {
            finish()
        }
    }
}