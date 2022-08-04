package com.chlqudco.develop.mydishlist.presentation.addrecord

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.databinding.ActivityAddRecordBinding
import com.chlqudco.develop.mydishlist.presentation.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class AddRecordActivity : BaseActivity<AddRecordViewModel, ActivityAddRecordBinding>() {

    override val viewModel by viewModel<AddRecordViewModel>()

    override fun getViewBinding(): ActivityAddRecordBinding = ActivityAddRecordBinding.inflate(layoutInflater)

    override fun observeData() {}

    private lateinit var getResult: ActivityResultLauncher<Intent>
    private var imageUri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if (result.resultCode == RESULT_OK){
                result.data?.data?.let {
                    contentResolver.takePersistableUriPermission(it,Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    //val bitmap = it as Bitmap
                    imageUri = it.toString()
                    //binding.addRecordPhotoImageView.setImageBitmap(bitmap)
                    binding.addRecordPhotoImageView.setImageURI(it)
                }
            }
        }
    }

    private fun initViews() {

        //사진 가져오기 버튼
        binding.addRecordGetPhotoButton.setOnClickListener {

            //권한 받아오기
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                navigatePhoto()
            }
            else{
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }

        }

        //추가 버튼
        binding.addRecordSaveButton.setOnClickListener {
            val title = binding.addRecordTitleEditText.text.toString()
            val rating = binding.addRecordRatingBar.rating
            val review = binding.addRecordReviewEditText.text.toString()
            val record = RecordEntity(null, title ,rating, System.currentTimeMillis(), review, imageUri)
            viewModel.addRecord(record)
            Toast.makeText(this,"추가 되었습니다.",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            1000 -> {
                if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    navigatePhoto()
                }else{
                    Toast.makeText(this, "권한 확인 필요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigatePhoto() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        getResult.launch(intent)
    }

}