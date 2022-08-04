package com.chlqudco.develop.mydishlist.presentation.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.databinding.ItemFoodBinding
import java.text.SimpleDateFormat
import java.util.*

class RecordListAdapter(val clickedListener: (RecordEntity) -> Unit): RecyclerView.Adapter<RecordListAdapter.RecordViewHolder>() {

    var recordList: List<RecordEntity> = mutableListOf()

    inner class RecordViewHolder(private val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(record: RecordEntity){
            binding.itemFoodRatingBar.rating = record.rating
            binding.itemFoodTitleTextView.text = record.title

            val date = Date(record.date)
            val sdf = SimpleDateFormat("yyyy/ MM/ dd")
            binding.itemFoodDateTextView.text = sdf.format(date)

            record.imageUrl?.let {
                binding.itemFoodImageView.setImageURI(Uri.parse(it))
            }

            //클릭 시 상세페이지 이동
            binding.root.setOnClickListener {
                clickedListener(record)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(
            ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(recordList[position])
    }

    override fun getItemCount(): Int {
        return recordList.size
    }

}