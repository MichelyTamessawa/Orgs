package com.example.orgs.database.converters

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun DoubleToBigDecimal(value: Double?): BigDecimal {
        return value?.let { BigDecimal(value.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun BigDecimalToDouble(value: BigDecimal?): Double? {
        return value?.let { value.toDouble() }
    }
}