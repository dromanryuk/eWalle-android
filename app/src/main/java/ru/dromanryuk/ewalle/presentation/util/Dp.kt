package ru.dromanryuk.ewalle.presentation.util

import android.content.res.Resources
import kotlin.math.roundToInt

@JvmInline
value class Dp(val value: Int) {

    val pixels: Float
        get() = value * Resources.getSystem().displayMetrics.density

    val roundedPixels: Int
        get() = pixels.roundToInt()

    operator fun times(value: Float) = Dp(
        (this.value * value).roundToInt()
    )
}

val Int.dp get() = Dp(this)