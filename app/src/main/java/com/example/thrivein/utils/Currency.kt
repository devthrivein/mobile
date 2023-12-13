package com.example.thrivein.utils

fun Int.toRpString(): String {
    val formatter = java.text.NumberFormat.getCurrencyInstance()
    formatter.currency = java.util.Currency.getInstance("IDR")
    return formatter.format(this.toLong())
}