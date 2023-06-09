package com.example.orgs.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Product(
    val name : String,
    val description : String,
    val value: BigDecimal,
    val imageUrl: String? = null
) : Parcelable
