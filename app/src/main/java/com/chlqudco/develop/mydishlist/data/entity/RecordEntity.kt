package com.chlqudco.develop.mydishlist.data.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id : Long,
    val title : String,
    val rating: Float
)
