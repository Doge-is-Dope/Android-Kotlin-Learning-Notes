package com.chunchiehliang.material3.ui.filter

import android.content.Context
import com.chunchiehliang.material3.R
import java.text.NumberFormat
import java.util.*

fun formatValueToCurrencyString(value: Float, currencyCode: String? = "TWD"): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance(currencyCode)
    return format.format(value.toInt())
}

fun formatCurrencyStringToLabel(context: Context, minValue: Float, maxValue: Float): String {
    val minCurrencyString = formatValueToCurrencyString(minValue)
    val maxCurrencyString = formatValueToCurrencyString(maxValue)
    return if (minValue == 0.0F && maxValue == 20000.0F) context.getString(R.string.any)
    else if (maxValue == 20000.0F) context.getString(R.string.above_value, minCurrencyString)
    else if (minValue == 0.0F) context.getString(R.string.up_to_value, maxCurrencyString)
    else context.getString(R.string.between_values, minCurrencyString, maxCurrencyString)
}