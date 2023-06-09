package com.example.orgs.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.brCurrencyFormatter() : String {
    val numberFormat : NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return numberFormat.format(this)
}