package com.example.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true) val uid : Long = 0L,
    val name : String,
    val description : String,
    val value: BigDecimal,
    val imageUrl: String? = null
) : Parcelable
