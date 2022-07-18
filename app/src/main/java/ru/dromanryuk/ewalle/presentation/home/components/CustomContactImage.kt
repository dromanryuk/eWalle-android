package ru.dromanryuk.ewalle.presentation.home.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import ru.dromanryuk.ewalle.R
import ru.dromanryuk.ewalle.presentation.util.Dp
import ru.dromanryuk.ewalle.presentation.util.dp
import kotlin.math.roundToInt

class CustomContactImage(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    var img = 0
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap by lazy { getImageBitmap() }

    private val size = 50.dp.roundedPixels.toFloat()
    private val halfSize = 25.dp.roundedPixels.toFloat()
    private var borderWidth = 2.dp.roundedPixels.toFloat()

    private fun getImageBitmap(): Bitmap {
        val bitmap = if (img != 0) {
            BitmapFactory.decodeResource(resources, img)
        } else {
            BitmapFactory.decodeResource(resources, R.drawable.contact_1)
        }
        val scalePx = bitmap.width.toFloat() / bitmap.height.toFloat()
        return Bitmap.createScaledBitmap(bitmap, (size * scalePx).roundToInt(), size.roundToInt(), false)
    }

    private var faceBackgroundColor = setBackgroundColor()
    private var borderColor = setBorderColor()

    private fun setBackgroundColor(): Int {
        val typedValue = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(R.attr.colorWithBackground, typedValue, true)
        return typedValue.data
    }

    private fun setBorderColor(): Int {
        val typedValue = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(R.attr.colorContactStroke, typedValue, true)
        return typedValue.data
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawFaceBackground(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas) {
        val offset = (size - bitmap.width).toInt().toFloat() / 2f

        paint.isAntiAlias = true
        paint.color = faceBackgroundColor
        paint.style = Paint.Style.FILL
        canvas.drawCircle(halfSize, halfSize, halfSize, paint)

        val path = Path()
        path.addCircle(
            halfSize, halfSize, size.coerceAtMost(halfSize), Path.Direction.CCW
        )
        canvas.clipPath(path)
        canvas.drawBitmap(bitmap, offset, 0f, null)

        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth
        canvas.drawCircle(halfSize, halfSize, halfSize - borderWidth / 2f, paint)
    }
}