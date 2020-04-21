package com.example.task_flair_solution.helpers

import android.R
import android.content.res.ColorStateList
import android.graphics.Color

object Constants{
    val CENTER_ITEM_CLICK_COLOR_STATE = ColorStateList(
        arrayOf(
            intArrayOf(-R.attr.state_checked), intArrayOf(
                R.attr.state_checked
            )
        ), intArrayOf(
            Color.parseColor("#000000"),
            Color.parseColor("#FFFFFF")
        )
    )

    val MAIN_COLOR_STATE = ColorStateList(
        arrayOf(
            intArrayOf(-R.attr.state_checked), intArrayOf(
                R.attr.state_checked
            )
        ), intArrayOf(
            Color.parseColor("#000000"),
            Color.parseColor("#000000")
        )
    )

    enum class Type {
        HIDE, SHOW
    }
}