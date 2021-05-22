package com.adityaikhbalm.libraries.ui.bottomnavigation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator.ofInt
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.Keep
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.adityaikhbalm.libraries.ui.R.styleable.*
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

class BottomNavigationIndicator(
    context: Context,
    attrs: AttributeSet?
) : View(context, attrs) {

    private var isLight: Boolean = false

    private val targetId: Int
    private var target: BottomNavigationMenuView? = null

    private var rect = Rect()
    private val backgroundDrawable: Drawable

    private var index = 0
    private var animator: AnimatorSet? = null

    init {
        if (attrs == null) {
            targetId = NO_ID
            backgroundDrawable = ColorDrawable(Color.TRANSPARENT)
        } else {
            with(context.obtainStyledAttributes(attrs, BottomNavigationIndicator)) {
                isLight = getBoolean(BottomNavigationIndicator_isLight, false)
                targetId = getResourceId(BottomNavigationIndicator_targetBottomNavigation, NO_ID)
                val clipableId = getResourceId(BottomNavigationIndicator_clipableBackground, NO_ID)
                backgroundDrawable =
                    if (clipableId != NO_ID) getDrawable(context, clipableId)
                        ?: ColorDrawable(Color.TRANSPARENT)
                    else ColorDrawable(
                        getColor(
                            BottomNavigationIndicator_clipableBackground,
                            Color.TRANSPARENT
                        )
                    )

                recycle()
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putParcelable("superState", super.onSaveInstanceState())
        bundle.putInt("position", index)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var bundle = state
        if (state is Bundle) {
            bundle = state
            index = bundle.getInt("position")
            bundle = bundle.getParcelable("superState")
        }
        super.onRestoreInstanceState(bundle)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (targetId == NO_ID)
            return attachedError("invalid target id $targetId")

        val parentView =
            parent as? View ?: return attachedError("Impossible to find the view using $parent")

        val child = parentView.findViewById<View?>(targetId)

        if (child !is BottomNavigationCustom) return attachedError("Invalid view $child")

        for (i in 0 until child.childCount) {
            val subView = child.getChildAt(i)
            if (subView is BottomNavigationMenuView) target = subView
        }
        elevation = child.elevation
        child.addOnNavigationItemSelectedListener {
            if (isLight)
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        updateRectByIndex(it, true)
                    },
                    200
                )
            else updateRectByIndex(it, true)
        }
        post { updateRectByIndex(index, false) }
    }

    private fun attachedError(message: String) {
        Log.e("BNVIndicator", message)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        target = null
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.clipRect(rect)
        backgroundDrawable.draw(canvas)
    }

    private fun updateRectByIndex(index: Int, animated: Boolean) {
        this.index = index
        target?.apply {
            if (childCount < 1 || index >= childCount) return

            val reference = getChildAt(index)

            val start = reference.left + left
            val end = reference.right + left

            val margin = (end - start) / 3
            val newRect = if (isLight) {
                backgroundDrawable.setBounds(start, top, end, bottom)
                Rect(start, top, end, height)
            } else {
                backgroundDrawable.setBounds(left, top, right, bottom)
                Rect(start + margin, top, end - margin, height)
            }

            if (animated) startUpdateRectAnimation(newRect) else updateRect(newRect)
        }
    }

    private fun startUpdateRectAnimation(rect: Rect) {
        animator?.cancel()
        animator = AnimatorSet().also {
            it.playTogether(
                ofInt(this, "rectLeft", this.rect.left, rect.left),
                ofInt(this, "rectRight", this.rect.right, rect.right),
                ofInt(this, "rectTop", this.rect.top, rect.top),
                ofInt(this, "rectBottom", this.rect.bottom, rect.bottom)
            )
            it.interpolator = FastOutSlowInInterpolator()
            it.duration = resources.getInteger(android.R.integer.config_longAnimTime).toLong()
            it.start()
        }
    }

    private fun updateRect(rect: Rect) {
        this.rect = rect
        postInvalidate()
    }

    @Keep
    fun setRectLeft(left: Int) = updateRect(rect.apply { this.left = left })

    @Keep
    fun setRectRight(right: Int) = updateRect(rect.apply { this.right = right })

    @Keep
    fun setRectTop(top: Int) = updateRect(rect.apply { this.top = top })

    @Keep
    fun setRectBottom(bottom: Int) = updateRect(rect.apply { this.bottom = bottom })
}
