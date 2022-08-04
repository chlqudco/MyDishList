package com.chlqudco.develop.mydishlist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.databinding.ItemFoodBinding

class RecordListAdapter: RecyclerView.Adapter<RecordListAdapter.ViewHolder>() {

    var recordList: List<RecordEntity> = listOf()
    private lateinit var recordItemClickListener: (RecordEntity) -> Unit

    inner class ViewHolder(
        val binding: ItemFoodBinding,
        val recordItemClickListener: (RecordEntity)-> Unit): RecyclerView.ViewHolder(binding.root) {

        fun bind(record: RecordEntity){
            binding.itemFoodRatingBar.rating = record.rating
            binding.itemFoodTitleTextView.text = record.title
        }

        fun bindViews(data: RecordEntity){
            binding.root.setOnClickListener {
                recordItemClickListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context), parent, false), recordItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recordList[position])
        holder.bindViews(recordList[position])
    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    fun setRecordList(recordList: List<RecordEntity>, recordItemClickListener: (RecordEntity) -> Unit = {}){
        this.recordList = recordList
        this.recordItemClickListener = recordItemClickListener
        notifyDataSetChanged()
    }
}