package com.chlqudco.develop.mydishlist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chlqudco.develop.mydishlist.data.entity.RecordEntity
import com.chlqudco.develop.mydishlist.databinding.ItemFoodBinding

class RecordListAdapter: RecyclerView.Adapter<RecordListAdapter.ViewHolder>() {

    private val recordList: List<RecordEntity> = listOf()

    inner class ViewHolder(val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(record: RecordEntity){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recordList[position])
    }

    override fun getItemCount(): Int {
        return recordList.size
    }
}