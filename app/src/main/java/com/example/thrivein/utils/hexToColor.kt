package com.example.thrivein.utils

import androidx.compose.ui.graphics.Color

fun hexStringToColor(hex: String): Color {
    return if (hex.length == 6) {
        Color(hex.toInt(radix = 16) or 0xff000000.toInt())
    } else {
        Color(hex.toLong(radix = 16) or 0xff000000.toLong())
    }
}