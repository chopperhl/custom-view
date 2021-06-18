package com.chopperhl.detail.test

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import java.lang.RuntimeException

/**
 * 纵向Linear
 */
class CustomViewGroup : ViewGroup {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {

        init(context)
    }

    constructor(context: Context, attributes: AttributeSet, style: Int) : super(context, attributes, style) {
        init(context)
    }


    private fun init(context: Context) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var usedHeight = 0
        var maxWidth = 0
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val wMode = MeasureSpec.getMode(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        val hMode = MeasureSpec.getMode(heightMeasureSpec)
        for (i in 0 until childCount) {
            val v = getChildAt(i)
            val lp: LayoutParams = v.layoutParams
            val childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width)
            val childHeightSpec = getChildMeasureSpec(heightMeasureSpec, usedHeight, lp.height)
            v.measure(childWidthSpec, childHeightSpec)
            val measuredHeight = v.measuredHeight
            usedHeight += measuredHeight
            if (v.measuredWidth > maxWidth) {
                maxWidth = v.measuredWidth
            }
        }

        var measuredWidth = 0
        var measuredHeight = 0
        //测量宽度
        when (wMode) {
            MeasureSpec.AT_MOST -> {
                if (maxWidth > w) {
                    measuredWidth = w
                } else {
                    measuredWidth = maxWidth
                }
            }
            MeasureSpec.EXACTLY -> {
                measuredWidth = w
            }
            else -> {
                throw RuntimeException("not support measureSpec")
            }
        }
        when (hMode) {
            MeasureSpec.AT_MOST -> {
                if (usedHeight > h) {
                    measuredHeight = h
                } else {
                    measuredHeight = usedHeight
                }
            }

            MeasureSpec.EXACTLY -> {
                measuredHeight = h
            }
            else -> {
                throw RuntimeException("not support measureSpec")
            }
        }

        setMeasuredDimension(measuredWidth, measuredHeight)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var startHeight = t
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            val h = view.measuredHeight
            val w = view.measuredWidth
            val bottom = startHeight + h
            var right = r
            if (l + w < r) {
                right = l + w
            }
            view.layout(l, t + startHeight, right, bottom)
            startHeight = bottom
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("TAG", "Group ---> onTouchEvent")
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("TAG", "Group ---> dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

}