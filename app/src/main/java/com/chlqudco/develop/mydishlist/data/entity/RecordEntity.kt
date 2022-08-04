package com.chlqudco.develop.mydishlist.data.entity

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id : Long?,
    @ColumnInfo val title : String,
    @ColumnInfo val rating: Float,
    @ColumnInfo val date: Long,
    @ColumnInfo val review: String,
    @ColumnInfo val imageUrl: String?
):Parcelable
