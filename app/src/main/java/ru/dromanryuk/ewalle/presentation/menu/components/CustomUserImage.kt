package ru.dromanryuk.ewalle.presentation.menu.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import ru.dromanryuk.ewalle.R
import kotlin.math.roundToInt

class CustomUserImage(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    var img = 0

    private var bWidth = dpToPx(42f)
    private var bHeight = dpToPx(42f)

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap by lazy { getImageBitmap() }

    private fun getImageBitmap(): Bitmap {
        val bitmap = if (img != 0) {
            BitmapFactory.decodeResource(resources, img)
        } else {
            BitmapFactory.decodeResource(resources, R.drawable.contact_1)
        }
        val scalePx = bitmap.width.toFloat() / bitmap.height.toFloat()
        return Bitmap.createScaledBitmap(bitmap, (bWidth * scalePx).toInt(), bHeight, false)
    }

    private var faceBackgroundColor = Color.parseColor("#F1F3F6")

    private fun dpToPx(dp: Float): Int {
        val displayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }

    private fun pxToDp(px: Float): Int {
        val displayMetrics = context.resources.displayMetrics
        return (px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawFaceBackground(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas) {
        val offset = (42f - pxToDp(bitmap.width.toFloat())) / 2f

        paint.isAntiAlias = true
        paint.color = faceBackgroundColor
        paint.style = Paint.Style.FILL
        val radius = bWidth / 2f
        canvas.drawCircle(bWidth / 2f, bHeight / 2f, radius, paint)

        val width = bWidth
        val height = bHeight
        val path = Path()
        path.addCircle(
            (width / 2).toFloat(), (height / 2).toFloat(), width.coerceAtMost(height / 2).toFloat(), Path.Direction.CCW
        )
        canvas.clipPath(path)
        canvas.drawBitmap(bitmap, dpToPx(offset).toFloat(), 0f, null)
    }

}