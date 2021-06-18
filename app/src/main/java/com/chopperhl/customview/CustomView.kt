package com.chopperhl.detail.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.lang.RuntimeException
import kotlin.math.min

class CustomView : View {
    private val paint by lazy {
        Paint().apply {
            color = Color.BLUE
        }
    }
    private val path by lazy {
        Path()
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, style: Int) : super(context, attributeSet, style) {
        init(context)
    }

    private fun init(context: Context) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val wMode = MeasureSpec.getMode(widthMeasureSpec)
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val hMode = MeasureSpec.getMode(heightMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        var measuredWidth = 0
        var measuredHeight = 0
        when (wMode) {
            MeasureSpec.EXACTLY -> {
                measuredWidth = w
            }
            MeasureSpec.AT_MOST -> {
                measuredWidth = 400
            }
            else -> {
                throw RuntimeException("un support measureSpec")
            }
        }
        when (hMode) {
            MeasureSpec.EXACTLY -> {
                measuredHeight = h
            }
            MeasureSpec.AT_MOST -> {
                measuredHeight = 400
            }
            else -> {
                throw RuntimeException("un support measureSpec")
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val w = measuredWidth
        val h = measuredHeight
        canvas?.drawCircle(w / 2f, h / 2f, min(w, h) / 2f, paint)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.d("TAG", "Child ---> dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("TAG", "Child ---> onTouchEvent")
        return super.onTouchEvent(event)
    }


}