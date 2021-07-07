package com.anit.goodweather.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.anit.goodweather.R
import kotlin.math.min

open class TransformCustomView : View {

    private var color = Color.BLUE
    private var strokeWidth = 8
    private var paint = Paint()

    private var paintText = Paint(Paint.ANTI_ALIAS_FLAG)
    private var fontSize = 40

    private var pressedView = false
    private var isCircle = true


    constructor(context: Context, attrs: AttributeSet?, defStyle: Int)
            : super(context, attrs, defStyle) {
        initAttr(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        initAttr(context, attrs)
    }


    private fun initAttr(context: Context, attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.TransformCustomView,
            0, 0
        )
        color = typedArray.getColor(R.styleable.TransformCustomView_cv_Color, Color.BLUE)
        strokeWidth = typedArray.getInt(R.styleable.TransformCustomView_cv_StrokeWidth, strokeWidth)
        typedArray.recycle()

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event!!.action
        if (action == MotionEvent.ACTION_DOWN) { // Нажали
            pressedView = true
            invalidate()           // Перерисовка элемента

        } else if (action == MotionEvent.ACTION_UP) {
            pressedView = false
            isCircle = !isCircle
            invalidate()           // Перерисовка элемента

        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            if (isCircle) {
                drawCircle(canvas)
            } else {
                drawRec(canvas)
            }

        }

    }

    private fun drawRec(canvas: Canvas) {
        paint.color = color
        if (pressedView) {
            paint.style = Paint.Style.FILL
        } else {
            paint.style = Paint.Style.STROKE
        }
        paint.strokeWidth = strokeWidth.toFloat()



        canvas.drawRect(
            strokeWidth.toFloat(),
            strokeWidth.toFloat(),
            (canvas.width).toFloat() - strokeWidth,
            (canvas.height).toFloat() - strokeWidth,
            paint
        )


        paintText.color = Color.BLACK
        paintText.textSize = fontSize.toFloat()

        canvas.drawText(
            context.getString(R.string.click_me),
            (canvas.width / 2).toFloat() - 70,
            (canvas.height / 2).toFloat(),
            paintText
        )
    }

    private fun drawCircle(canvas: Canvas) {
        paint.color = color

        if (pressedView) {
            paint.style = Paint.Style.FILL
        } else {
            paint.style = Paint.Style.STROKE
        }


        paint.strokeWidth = strokeWidth.toFloat()

        val radius =
            min(canvas.height.toFloat() / 2, canvas.height.toFloat() / 2) - strokeWidth

        canvas.drawCircle(
            (canvas.width / 2).toFloat(),
            (canvas.height / 2).toFloat(),
            radius,
            paint
        )


        paintText.color = Color.BLACK
        paintText.textSize = fontSize.toFloat()

        canvas.drawText(
            context.getString(R.string.click_me),
            (canvas.width / 2).toFloat() - 70,
            (canvas.height / 2).toFloat(),
            paintText
        )

    }



}


